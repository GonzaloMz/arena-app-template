package app.backend.deserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.backend.model.Place;
import app.backend.model.User;

@JsonComponent
public class PlaceDeserializer  extends JsonDeserializer<Place>{
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public Place deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
	      TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
	      if(treeNode.isContainerNode())
	    	  return mapper.convertValue(treeNode, Place.class);
	      else {
	    	  Place place = new Place();
	    	  place.setId(Long.valueOf(treeNode.toString()));
	    	  return place;
	      }
	}

}
