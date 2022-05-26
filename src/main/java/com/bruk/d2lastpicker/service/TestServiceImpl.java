package com.bruk.d2lastpicker.service;

import com.bruk.d2lastpicker.dto.PublicMatchData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class TestServiceImpl implements TestService {

    private static final Logger LOG = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public List<PublicMatchData> getPublicMatches() {
        try {

            String sURL = "https://api.opendota.com/api/publicMatches";
            LOG.info("calling URL", sURL);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(sURL))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                LOG.error("Unable to contact remote server. Response code: ", response.statusCode());
                return new ArrayList<PublicMatchData>();
            } else {
                String json = response.body();

                LOG.debug("Mapping JSON onto Object");
                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                List<PublicMatchData> matchesList = mapper.readValue(json, new TypeReference<List<PublicMatchData>>() {
                });
                return matchesList;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

