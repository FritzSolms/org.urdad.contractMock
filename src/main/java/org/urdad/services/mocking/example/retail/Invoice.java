/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.urdad.services.mocking.example.retail;

import java.util.Map;

/**
 *
 * @author fritz@solms.co.za
 */
public class Invoice implements Cloneable
{
    
    
    private Map<String, Item> items;
    
    public static class Item
    {
       private Order.Item orderItem;
    }
}
