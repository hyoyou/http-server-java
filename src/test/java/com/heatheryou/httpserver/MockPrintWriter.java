package com.heatheryou.httpserver;

import java.util.ArrayList;
import java.util.List;

public class MockPrintWriter {
    private List<String> outputs = new ArrayList<>();

    void println(String output) {
        outputs.add(output);
    }

    List<String> getOutputs() {
        return outputs;
    }
}
