
package com.linghushaoxia.video.manager.impl.mgtv.dto;
import java.util.List;


public class Points {

    private List<String> content;
    private String end;
    private String start;
    public void setContent(List<String> content) {
         this.content = content;
     }
     public List<String> getContent() {
         return content;
     }

    public void setEnd(String end) {
         this.end = end;
     }
     public String getEnd() {
         return end;
     }

    public void setStart(String start) {
         this.start = start;
     }
     public String getStart() {
         return start;
     }

}