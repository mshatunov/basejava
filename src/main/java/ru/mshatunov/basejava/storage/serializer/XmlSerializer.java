package ru.mshatunov.basejava.storage.serializer;

import ru.mshatunov.basejava.model.*;
import ru.mshatunov.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializer implements StreamSerializer {

    private XmlParser xmlParser;

    public XmlSerializer() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class, ListSection.class, Organization.Position.class);
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is)) {
            return xmlParser.unmarshall(r);
        }
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }
}
