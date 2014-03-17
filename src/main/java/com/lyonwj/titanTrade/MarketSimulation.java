package com.lyonwj.titanTrade;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.util.TitanCleanup;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Direction;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 * Simulates simple graph based trading market
 *
 *
 */
public class MarketSimulation
{
    public static void main( String[] args )
    {
        Configuration conf = new BaseConfiguration();
        conf.setProperty("storage.backend","cassandra");
        conf.setProperty("storage.hostname","127.0.0.1");

        TitanGraph g = TitanFactory.open(conf);
        g.shutdown();
        TitanCleanup.clear(g);

        g = TitanFactory.open(conf);


        Vertex s1 = g.addVertex(null);
        s1.setProperty("name", "s1");
        s1.setProperty("type", "seller");

        Vertex s2 = g.addVertex(null);
        s2.setProperty("name", "s2");
        s2.setProperty("type", "seller");

        Vertex s3 = g.addVertex(null);
        s3.setProperty("name", "s3");
        s3.setProperty("type", "seller");

        Vertex t1 = g.addVertex(null);
        t1.setProperty("name", "t1");
        t1.setProperty("type", "trader");

        Vertex t2 = g.addVertex(null);
        t2.setProperty("name", "t2");
        t2.setProperty("type", "trader");

        Vertex b1 = g.addVertex(null);
        b1.setProperty("name", "b1");
        b1.setProperty("type", "buyer");

        Vertex b2 = g.addVertex(null);
        b2.setProperty("name", "b2");
        b2.setProperty("type", "buyer");

        Vertex b3 = g.addVertex(null);
        b3.setProperty("name", "b3");
        b3.setProperty("type", "buyer");

        Edge e = g.addEdge(null, s1, t1, "bid");
        e.setProperty("weight",0.2);

        e = g.addEdge(null, s2, t1, "bid");
        e.setProperty("weight", 0.2);

        e = g.addEdge(null, s2, t2, "bid");
        e.setProperty("weight", 0.3);

        e = g.addEdge(null, s3, t2, "bid");
        e.setProperty("weight", 0.0);

        e = g.addEdge(null, t1, b1, "ask");
        e.setProperty("weight", 0.8);

        g.addEdge(null, t1, b2, "ask").setProperty("weight", 0.8);
        g.addEdge(null, t2, b2, "ask").setProperty("weight", 0.7);
        g.addEdge(null, t2, b3, "ask").setProperty("weight", 1.0);

        Iterable<Vertex> results = s1.query().vertices();


        // Print name of all Traders connected to s1
        // t1
        for (Vertex result : results){
            System.out.println(result.getProperty("name"));
        }

        // Print name of all buyers connected to s1 through all Traders connected to s1
        // b1
        // b2

        for (Edge edge : s1.getEdges(Direction.OUT)){
            for (Edge outEdge : edge.getVertex(Direction.IN).getEdges(Direction.OUT)) {
                System.out.println(outEdge.getVertex(Direction.IN).getProperty("name"));
            }
        }
    }
}
