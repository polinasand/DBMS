package app.util;

public class Link {
        private String method;
        private String rel;
        private String href;
        public Link(String method, String rel, String href) {
            this.href = href;
            this.rel = rel;
            this.method = method;
        }

        public static String addProperty(String data, Link[] links) {
            String jsonLinks = Serializer.toJson(links);
            data = data.substring(0, data.length()-1);
            return data + ", \"links\":"+ jsonLinks +"}";
        }

}
