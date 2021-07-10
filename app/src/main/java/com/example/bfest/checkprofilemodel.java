package com.example.bfest;

public class checkprofilemodel {





String FieldofStudy,StallName ,GroupMembers,LeaderName,MenuItems,logourl,Semester,StallLocationid;


    public checkprofilemodel(){


    }

    public checkprofilemodel(String fieldofStudy, String stallName, String groupMembers, String leaderName, String menuItems, String logourl, String semester, String stallLocationid) {
        FieldofStudy = fieldofStudy;
        StallName = stallName;
        GroupMembers = groupMembers;
        LeaderName = leaderName;
        MenuItems = menuItems;
        this.logourl = logourl;
        Semester = semester;
        StallLocationid = stallLocationid;
    }

    public String getFieldofStudy() {
        return FieldofStudy;
    }

    public void setFieldofStudy(String fieldofStudy) {
        FieldofStudy = fieldofStudy;
    }

    public String getStallName() {
        return StallName;
    }

    public void setStallName(String stallName) {
        StallName = stallName;
    }

    public String getGroupMembers() {
        return GroupMembers;
    }

    public void setGroupMembers(String groupMembers) {
        GroupMembers = groupMembers;
    }

    public String getLeaderName() {
        return LeaderName;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }

    public String getMenuItems() {
        return MenuItems;
    }

    public void setMenuItems(String menuItems) {
        MenuItems = menuItems;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getStallLocationid() {
        return StallLocationid;
    }

    public void setStallLocationid(String stallLocationid) {
        StallLocationid = stallLocationid;
    }
}

