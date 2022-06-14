package com.bruk.d2lastpicker.service;


import com.bruk.d2lastpicker.dto.HeroData;
import com.bruk.d2lastpicker.dto.HeroMatchupData;
import com.bruk.d2lastpicker.dto.PlayerHeroData;
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
public class Dota2APIServiceImpl implements Dota2APIService {

    public static final Logger LOG = LoggerFactory.getLogger(Dota2APIServiceImpl.class);



    @Override
    public List<PlayerHeroData> getPlayerHeroData(long playerId) {
        try {

            String sURL = String.format("https://api.opendota.com/api/players/%d/heroes", playerId);
            LOG.info("calling URL", sURL);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(sURL))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                LOG.error("Unable to contact remote server. Response code: ", response.statusCode());
                return new ArrayList<PlayerHeroData>();
            } else {
                String json = response.body();

                LOG.debug("Mapping JSON onto Object");
                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                List<PlayerHeroData> matchesList = mapper.readValue(json, new TypeReference<List<PlayerHeroData>>() {});
                return matchesList;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<HeroData> getHeroData() {
        try {

            String sURL = "https://api.opendota.com/api/heroes";

            LOG.debug(String.format("calling URL %s", sURL));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(sURL))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                LOG.error("Unable to contact remote server. Response code: ", response.statusCode());
                return new ArrayList<HeroData>();
            } else {
                String json = response.body();

                LOG.debug("Mapping JSON onto List<HeroData>");
                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                List<HeroData> matchesList = mapper.readValue(json, new TypeReference<List<HeroData>>() {});
                return matchesList;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HeroMatchupData> getHeroMatchupData(long heroId)
    {

        try {
            String sURL = String.format("https://api.opendota.com/api/heroes/%d/matchups", heroId);
            LOG.debug(String.format("calling URL %s", sURL));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(sURL))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                LOG.error("Unable to contact remote server. Response code: ", response.statusCode());
                return new ArrayList<HeroMatchupData>();
            } else {
                String json = response.body();

                LOG.debug("Mapping JSON onto List<HeroMatchupData>");
                ObjectMapper mapper = new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                List<HeroMatchupData> matchesList = mapper.readValue(json, new TypeReference<List<HeroMatchupData>>() {});
                return matchesList;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }


    }


