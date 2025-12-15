package ua.lz.ep.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import ua.lz.ep.model.Item;


public interface ItemSearchService {

    public void index(String id, String description, String category, float price) throws SolrServerException, IOException;

    public void indexBean(Item item) throws IOException, SolrServerException;

}
