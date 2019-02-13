package com.heatheryou.httpserver.handler;

import com.heatheryou.httpserver.Request;
import com.heatheryou.httpserver.constants.CharacterSet;
import com.heatheryou.httpserver.constants.EntityHeader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsHandlerTest {
    @Test
    public void getAllowedMethodsListIsAbleToRetrieveAListOfAllowedMethodsProvidedTheRequest() {
        OptionsHandler handler = new OptionsHandler();
        Request request = new Request("/simple_get", "GET");
        String actual = handler.getAllowedMethodsList(request);
        String expected = "GET,HEAD";
        assertEquals(expected, actual);
    }

    @Test
    public void getAllowedMethodsIsAbleToBuildEntityHeaderAllowedMethodsProvidedTheRequest() {
        OptionsHandler handler = new OptionsHandler();
        Request request = new Request("/simple_get", "GET");
        String actual = handler.getAllowedMethods(request);
        String expected = EntityHeader.ALLOWED_METHODS + CharacterSet.SPACE + "GET,HEAD" + CharacterSet.CRLF;
        assertEquals(expected, actual);
    }

    @Test
    public void getContentLengthIsAbleToPrintOutContentLengthEntityHeader() {
        OptionsHandler handler = new OptionsHandler();
        String actual = handler.getContentLength();
        String expected = EntityHeader.CONTENT_LENGTH + CharacterSet.SPACE + EntityHeader.CONTENT_LENGTH_0 + CharacterSet.CRLF;
        assertEquals(expected, actual);
    }
}