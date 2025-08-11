package com.nexus.web.common;

public class MapDocumet {

    private String name;
    private String key;
    private String displayname;
    
    
    public MapDocumet(String key, String name, String displayname) {
            this.key = key;
            this.name = name;
            this.displayname = displayname;
        }
    public MapDocumet() {
            
        }
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return this.displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
    public boolean equals(Object obj) {
            if (! (obj instanceof MapDocumet)) {
                return false;
            }
            else {
                return key.equals(((MapDocumet)obj).getKey());
            }
        }

        public int hashCode() {
            return key.hashCode();
        }
        
}
