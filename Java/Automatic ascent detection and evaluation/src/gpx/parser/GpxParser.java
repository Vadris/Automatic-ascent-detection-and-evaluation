package gpx.parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import gpx.GpxData;
import gpx.Track;
import gpx.TrackPoint;
import gpx.TrackSegment;

public class GpxParser {
    public static GpxData parse(File input) throws GpxParseException{
        GpxData data = new GpxData();
        
        SAXBuilder parser = new SAXBuilder();
        Document gpxDocument;
        try{
            gpxDocument = parser.build(input);
        }catch(IOException ioException){
            throw new GpxParseException("Failed to parse gpx file.\n" + ioException.getMessage());
        }catch(JDOMException jdomException){
            throw new GpxParseException("Failed to parse gpx file.\n" + jdomException.getMessage());
        }

        Element rootNode = gpxDocument.getRootElement();
        Namespace documentNamespace = rootNode.getNamespace();

        //TODO: Parse Date & Time
        String[] timeDateMetaData = rootNode.getChildText("time", documentNamespace).split("T");
        String[] date = timeDateMetaData[0].split("-");
        String[] time = timeDateMetaData[1].split(":");
        data.setDate(LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])));
        data.setTime(LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])));

        List<Element> trackNodes = rootNode.getChildren("trk", documentNamespace);
        for(int i = 0; i < trackNodes.size(); i++){
            data.addTrack(parseTrack(trackNodes.get(i), documentNamespace));
        }
        return data;
    }
    private static Track parseTrack(Element trackNode, Namespace documentNamespace){
        Track track = new Track();
        List<Element> trackSegmentNodes = trackNode.getChildren("trkseg", documentNamespace);
        for(int i = 0; i < trackSegmentNodes.size(); i++){
            track.addTrackSegment(parseTrackSegment(trackSegmentNodes.get(i), documentNamespace));
        }
        return track;
    }
    private static TrackSegment parseTrackSegment(Element trackSegmentNode, Namespace documentNamespace) {
        TrackSegment trackSegment = new TrackSegment();
        List<Element> trackPointNodes = trackSegmentNode.getChildren("trkpt", documentNamespace);
        for(int i = 0; i < trackPointNodes.size(); i++){
            double latitude = 0;
            double longitude = 0;
            double elevation = 0;
            try{
                latitude = trackPointNodes.get(i).getAttribute("lat").getDoubleValue() * Math.PI/180;
                longitude = trackPointNodes.get(i).getAttribute("lon").getDoubleValue() * Math.PI/180;
            }catch(DataConversionException e){
                e.printStackTrace();
                continue;
            }
            if(!Objects.isNull(trackPointNodes.get(i).getChildText("ele", documentNamespace))){
                    elevation = Double.parseDouble(trackPointNodes.get(i).getChildText("ele", documentNamespace));
            }
            trackSegment.addTrackPoint(new TrackPoint(latitude, longitude, elevation));
        }
        return trackSegment;
    }
}