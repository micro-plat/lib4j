package  com.lib4j.mq;

import java.util.Map;

public interface IQueue {
   void push(String name, String data);
   void push(String name, Object data);
   String pop(String name);
   String popAndWait(String name,long seconds);
   Map<String, Object> popMap(String name);
   Map<String, Object> popMap(String name,long seconds);
}
