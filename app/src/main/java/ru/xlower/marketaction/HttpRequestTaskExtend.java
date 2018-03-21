package ru.xlower.marketaction;

/**
 * Created by Dmitry.Shishlo on 21.03.2018.
 */

//import javax.net.ssl.HttpsURLConnection;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import android.net.http.HttpResponseCache;
//import ru.xlower.marketaction.model.Good;

import java.lang.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ru.xlower.marketaction.model.Good;

public class HttpRequestTaskExtend extends HttpRequestTask
{
    HttpRequestTask httpRequestTask;
    public HttpRequestTaskExtend()
    {
        httpRequestTask = new HttpRequestTask();
    }
}

class HttpRequestTask extends AsyncTask<Object, Void, List<Good>>
{
    protected String RESPONSE = "";
    private URL url;
    //тут можно заменить тип метода (Good) на тот тип который хочется получать ответом
    protected List<Good> doInBackground(Object... params)
    {
        try
        {//5f69f2dd-8de7-4a66-ac6a-abd3b3eddc60
            this.url = new URL("http://xlower.ru:7082/api/goods/");
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            /*
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            HashMap<String, Object> urlVariables = new HashMap<String, Object>();
            urlVariables.put("codEmp", params);
            return restTemplate;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("text/plain")));
            HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
            this.RESPONSE = restTemplate.exchange(this.url.toString(),HttpMethod.GET, requestEntity, String.class,urlVariables).getBody();
            */

        //List<Good>                           goods          = restTemplate.getForEntity(url, Good[].class);
        List<Good> goods = restTemplate.getForEntity(url.toString(), List.class).getBody();
        return goods;
    }
        catch (Exception e)
        {
            Log.e("MainActivity", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Good> _goods)
    {

    }
}