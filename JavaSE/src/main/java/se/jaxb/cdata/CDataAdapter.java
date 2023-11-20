package se.jaxb.cdata;


import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Optional;

public class CDataAdapter extends XmlAdapter<String, String> {

    private static final String CDATA_PREFIX = "<![CDATA[";
    private static final String CDATA_SUFFIX = "]]>";

    @Override
    public String unmarshal(String v) {
        return v;
    }

    @Override
    public String marshal(String v) {
        return CDATA_PREFIX + Optional.ofNullable(v).orElse("") + CDATA_SUFFIX;
    }
}
