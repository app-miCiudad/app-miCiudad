package miCiudad.modules.miCiudad.dom.reporte;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.value.Blob;

import miCiudad.modules.miCiudad.dom.empresa.Empresa;
import miCiudad.modules.miCiudad.dom.obra.Obra;
import miCiudad.modules.miCiudad.dom.obra.Obras;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@DomainService(nature = NatureOfService.VIEW)
@Named(Obra.NAMESPACE + ".ReporteObra")
@DomainServiceLayout(named = "Reporte")

public class Reportes {

    @Inject Obras obrasServices;  
    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public Blob generarReporteObras() throws JRException {
     
        List <Obra> listAll = obrasServices.listAll();
        List <ReporteObras> data = new ArrayList<>();

        for( Obra obra : listAll){
            ReporteObras reporteObras1 = new ReporteObras();
            reporteObras1.setTitulo(obra.getTitulo().toString());
            reporteObras1.setTipo(obra.getTipo().toString());
            reporteObras1.setPresupuesto(obra.getPresupuesto());
                   
            Empresa empresa = obra.getEmpresa();
            if (empresa != null) {
                reporteObras1.setNombreEmpresa(empresa.getNombre());
            } else {
                reporteObras1.setNombreEmpresa("Empresa no especificada");
            }

            reporteObras1.setFechaInicio(obra.getFechaInicio());
            reporteObras1.setFechaFinal(obra.getFechaFinal());
            reporteObras1.setEstado(obra.getEstado().toString()); 
            data.add(reporteObras1);
        }

        List<ReporteObras> ListaObras = data;
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ListaObras", ListaObras);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
        return generador("Obras.jrxml", ds, "Obra");
   } 
    
    public Blob generador( String archivoDesing, JRBeanCollectionDataSource ds, String nombre) throws JRException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("miCiudad/modules/miCiudad/dom/reporte/Obras.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);          
        JasperReport archivoCompilado = JasperCompileManager.compileReport(jasperDesign);     
        JasperPrint jasperPrint = JasperFillManager.fillReport(archivoCompilado, null, ds);
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
        
        String rutaGuardado = "C:/Users/CallREX/Desktop/InformeObras.pdf";
        try (FileOutputStream fos = new FileOutputStream(rutaGuardado)) {
            fos.write(pdfBytes);
            } catch (IOException e) {
                e.printStackTrace();
        }    
        return new Blob("Reporte " + nombre + ".pdf", "application/pdf", pdfBytes);

    }   
}
