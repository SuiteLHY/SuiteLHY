package spring.example.SpringDI;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class AddressCollection {

    List<Address> addressList;

    Set<Address> addressSet;

    Map<Integer, Address> addressMap;

    // java.util.Properties 使用了已被淘汰的 java.util.Hashtable 类,
    // -> 故应该弃用;此处由于考虑到需要演示 Spring 注入集合功能提供的
    // -> <props>标签的使用方法,故保留.
    Properties addressProperties;

    List<String> addressNameList;

    Set<String> addressNameSet;

    Map<String, String> addressInfoMap;

    //===== getter and setter =====//
    public void setAddressList(List addressList) {
        this.addressList = addressList;
    }

    public List<Address> getAddressList() {
        System.out.println("List Elements : " + addressList);
        return addressList;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    public Set<Address> getAddressSet() {
        System.out.println("Set Elements : " + addressSet);
        return addressSet;
    }

    public void setAddressMap(Map<Integer, Address> addressMap) {
        this.addressMap = addressMap;
    }

    public Map<Integer, Address> getAddressMap() {
        System.out.println("Map Elements : " + addressMap);
        return addressMap;
    }

    public void setAddressProperties(Properties addressProperties) {
        this.addressProperties = addressProperties;
    }

    public Properties getAddressProperties() {
        System.out.println("Properties Element : " + addressProperties);
        return addressProperties;
    }

    public List<String> getAddressNameList() {
        System.out.println("List Element : " + addressNameList);
        return addressNameList;
    }

    public void setAddressNameList(List<String> addressNameList) {
        System.out.println("List Element : " + addressNameList);
        this.addressNameList = addressNameList;
    }

    public Set<String> getAddressNameSet() {
        System.out.println("Set Element : " + addressNameSet);
        return addressNameSet;
    }

    public void setAddressNameSet(Set<String> addressNameSet) {
        this.addressNameSet = addressNameSet;
    }

    public Map<String, String> getAddressInfoMap() {
        System.out.println("Map Element : " + addressInfoMap);
        return addressInfoMap;
    }

    public void setAddressInfoMap(Map<String, String> addressInfoMap) {
        this.addressInfoMap = addressInfoMap;
    }

}
