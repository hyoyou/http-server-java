package com.heatheryou.httpserver.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsHandlerTest {
    @Test
    public void ItIsAbleToRetrieveAListOfAllowedMethodsProvidedTheRequest() {
        OptionsHandler handler = new OptionsHandler();
        Request request = new Request("/simple_get", "GET");
        String actual = handler.getAllowedMethodsList(request);
        String expected = "GET,HEAD";
        assertEquals(expected, actual);
    }

    @Test
    public void ItIsAbleToBuildEntityHeaderAllowedMethodsProvidedTheRequest() {
        OptionsHandler handler = new OptionsHandler();
        Request request = new Request("/simple_get", "GET");
        String actual = handler.getAllowedMethods(request);
        String expected = EntityHeader.ALLOWED_METHODS + CharacterSet.SP + "GET,HEAD" + CharacterSet.CRLF;
        assertEquals(expected, actual);
    }

    @Test
    public void ItIsAbleToPrintOutContentLengthEntityHeader() {
        OptionsHandler handler = new OptionsHandler();
        String actual = handler.getContentLength();
        String expected = EntityHeader.CONTENT_LENGTH + CharacterSet.SP + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
        assertEquals(expected, actual);
    }
}