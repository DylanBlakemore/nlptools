package nlptools.realisation.containers;

public class XmlHelper {

  public static String wrap(String tag, String properties, String content) {
    StringBuilder xml = new StringBuilder();
    xml.append("<").append(tag);
    
    if (properties != null) {
      xml.append(" ").append(properties);
    }

    xml
      .append(">")
      .append(content)
      .append("</")
      .append(tag)
      .append(">");

    return xml.toString();
  }

  public static String root(String cat, String base) {
    return new StringBuilder()
                .append("<head cat=\"")
                .append(cat)
                .append("\"><base>")
                .append(base)
                .append("</base></head>")
                .toString();
  }

  public static String elementProperties(String xsiType, Feature[] features) {
    StringBuilder builder = new StringBuilder();
    builder
      .append("xsi:type=\"")
      .append(xsiType)
      .append("\"");

    for (Feature feature : features) {
      builder
        .append(" ")
        .append(feature.name)
        .append("=\"")
        .append(feature.value)
        .append("\"");
    }
    return builder.toString();
  }
  
}
