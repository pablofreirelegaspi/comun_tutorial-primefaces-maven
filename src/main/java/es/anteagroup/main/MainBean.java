package es.anteagroup.main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;





@ManagedBean
@ViewScoped
public class MainBean implements Serializable {

    private ScheduleModel scheduleModel;
    
    private TimelineModel<String, ?> timeLineModel;
    private LocalDateTime timeLineStart;
    private LocalDateTime timeLineEnd;
    private Long timeLineZoomMin;
    private Long timeLineZoomMax;
    
    private MindmapNode mindMapRootNode;
    private MindmapNode mindMapSelectedNode;
    
    
    public MainBean() {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // iniciarlizar el control schedule
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.scheduleModel = new DefaultScheduleModel();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // iniciarlizar el control time line
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.timeLineModel = new TimelineModel<>();
        
        // set initial start / end dates for the axis of the timeline
        this.timeLineZoomMin = 1000L * 60 * 60 * 12;
        this.timeLineZoomMax = 1000L * 60 * 60 * 24 * 7;
        
        // set initial start / end dates for the axis of the timeline
        this.timeLineStart = LocalDate.now().atStartOfDay();
        this.timeLineEnd = LocalDate.now().plusDays(2).atStartOfDay();

        // groups
        String[] names = new String[]{"Operador 001", "Operador 002", "Operador 003", "Operador 004", "Operador 005", "Operador 006", "Operador 007", "Operador 008", "Operador 009", "Operador 010"};

        // create timeline model
        this.timeLineModel = new TimelineModel<>();

        for (String name : names) {
            LocalDateTime end = this.timeLineStart.minusHours(12).withMinute(0).withSecond(0).withNano(0);

            for (int i = 0; i < 5; i++) {
                LocalDateTime start = end.plusHours(Math.round(Math.random() * 5));
                end = start.plusHours(4 + Math.round(Math.random() * 5));

                long r = Math.round(Math.random() * 2);
                String availability = (r == 0 ? "Unavailable" : (r == 1 ? "Available" : "Maybe"));

                // create an event with content, start / end dates, editable flag, group name and custom style class
                TimelineEvent event = TimelineEvent.builder()
                        .data(availability)
                        .title(getFechaFormateada(start, end))
                        .startDate(start)
                        .endDate(end)
                        .editable(true)
                        .group(name)
                        .styleClass(availability.toLowerCase())
                        .build();

                this.timeLineModel.add(event);
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // iniciarlizar el control mindmap
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.mindMapRootNode = new DefaultMindmapNode("google.com", "Google WebSite", "FFCC00", false);
        MindmapNode ips = new DefaultMindmapNode("IPs", "IP Numbers", "6e9ebf", true);
        MindmapNode ns = new DefaultMindmapNode("NS(s)", "Namespaces", "6e9ebf", true);
        MindmapNode malware = new DefaultMindmapNode("Malware", "Malicious Software", "6e9ebf", true);
        this.mindMapRootNode.addNode(ips);
        this.mindMapRootNode.addNode(ns);
        this.mindMapRootNode.addNode(malware);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
    }

    
    private String getFechaFormateada(LocalDateTime localDateTimeIni, LocalDateTime localDateTimeFin){

        DateTimeFormatter dateTimeFormatIni = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");
        DateTimeFormatter dateTimeFormatFin = DateTimeFormatter.ofPattern("HH");
        return localDateTimeIni.format(dateTimeFormatIni) + "h-" + localDateTimeFin.format(dateTimeFormatFin) + "h";

    }
    

    public void onNodeSelect(SelectEvent<MindmapNode> event) {
        MindmapNode node = event.getObject();

        //populate if not already loaded
        if (node.getChildren().isEmpty()) {
            Object label = node.getLabel();

            if (label.equals("NS(s)")) {
                for (int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
                }
            }
            else if (label.equals("IPs")) {
                for (int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1." + i, "IP Number: 1.1.1." + i, "fce24f", false));
                }
            }
            else if (label.equals("Malware")) {
                for (int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-" + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }
    }

    public void onNodeDblselect(SelectEvent<MindmapNode> event) {
        this.mindMapSelectedNode = event.getObject();
    }    
    
    

    /**
     * @return the scheduleModel
     */
    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    /**
     * @param scheduleModel the scheduleModel to set
     */
    public void setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
    }

    /**
     * @return the timeLineModel
     */
    public TimelineModel<String, ?> getTimeLineModel() {
        return timeLineModel;
    }

    /**
     * @param timeLineModel the timeLineModel to set
     */
    public void setTimeLineModel(TimelineModel<String, ?> timeLineModel) {
        this.timeLineModel = timeLineModel;
    }

    public LocalDateTime getTimeLineStart() {
        return timeLineStart;
    }

    public LocalDateTime getTimeLineEnd() {
        return timeLineEnd;
    }
    
    public MindmapNode getMindMapRootNode() {
        return mindMapRootNode;
    }

    public MindmapNode getMindMapSelectedNode() {
        return mindMapSelectedNode;
    }

    public void setMindMapSelectedNode(MindmapNode mindMapSelectedNode) {
        this.mindMapSelectedNode = mindMapSelectedNode;
    }

    /**
     * @return the timeLineZoomMin
     */
    public Long getTimeLineZoomMin() {
        return timeLineZoomMin;
    }

    /**
     * @return the timeLineZoomMax
     */
    public Long getTimeLineZoomMax() {
        return timeLineZoomMax;
    }

}