package ru.xlower.marketaction.Services;

import java.lang.*;
import java.io.*;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.Enumeration;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ru.xlower.marketaction.HttpRequestTaskExtend;
import ru.xlower.marketaction.model.Good;

/**
 * Created by xLOWer on 21.03.2018.
 */

public class GoodService
{
    private GetGoods GoodGetService;

    private GoodService()
    {
        GetGoods GoodGetService = new GetGoods();
    }

    public List<Good> GetGoods() throws Exception
    {
        return GoodGetService.execute().get();
    }

    public List<Good> GetGood(@Nullable String _id) throws Exception
    {
        _id = _id == null ? "5f69f2dd-8de7-4a66-ac6a-abd3b3eddc60" : _id;
        return GoodGetService.execute(_id).get();
    }

    @SuppressLint("StaticFieldLeak")
    class GetGoods extends AsyncTask<String, Void, List<Good>>
    {
        private final String GoodStringUrl = "http://xlower.ru:7082/api/goods/";
        private URL url;

        protected List<Good> doInBackground(String... _params)
        {
            try
            {
                if(_params.length != 0)
                {
                    String __id = "";
                    for(String param : _params)
                        __id += param;
                    this.url = new URL(GoodStringUrl + __id);
                }
                else
                    this.url = new URL(GoodStringUrl);

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
    }
}
