package ru.xlower.marketaction;

/**
 * Created by Dmitry.Shishlo on 21.03.2018.
 */

import java.lang.*;
import java.io.*;
import java.net.URL;
import java.util.List;
import android.os.AsyncTask;
import android.util.Log;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ru.xlower.marketaction.model.Good;

public class HttpRequestTaskExtend extends HttpRequestTask
{
    HttpRequestTask httpRequestTask;
    public HttpRequestTaskExtend()
    {
        httpRequestTask = new HttpRequestTask();
        httpRequestTask.httpRequestTaskExtend = this;
    }
    public void Get(){}
}

class HttpRequestTask extends AsyncTask<String, Void, List<Good>>
{
    protected HttpRequestTaskExtend httpRequestTaskExtend;
    protected String RESPONSE = "";
    protected URL url;
    //тут можно заменить тип метода (Good) на тот тип который хочется получать ответом
    protected List<Good> doInBackground(String... _params)
    {
        try
        {
            String __url = "";

            if(_params.length == 0)
                return null;

            for(String param : _params)
                __url += param;

            if(__url == "")
                return null;

            this.url =new URL(__url);//new URL("http://xlower.ru:7082/api/goods/");//5f69f2dd-8de7-4a66-ac6a-abd3b3eddc60
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
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