/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataUtil;

import comunnication.CommunicationReasonsEnum;
import comunnication.ServerComunnicationModel;
import cruds.CrudBestscores;
import cruds.CrudSkin;
import cruds.CrudUser;
import entity.Bestscores;
import entity.Skins;
import entity.User;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import server.Server;

/**
 *
 * @author vikto
 */
public class DataUtil {

    private static List<User> allusers;

    public static void checkMethodForDatabase(Socket socket, ServerComunnicationModel model) {
        switch (model.getReason()) {
            case LOGIN:
                login(socket, model);
                break;
            case REGISTER:
                register(socket, model);
                break;
            case MOVE:
                move(socket, model);
                break;
            case CHANGESKIN:
                changeSkin(socket, model);
                break;
//            case CHECKPAUSE:
//                checkPause(socket, model);
//                break;
            case SETSCORE:
                setScore(socket, model);
                break;
            case READSCORE:
                readScore(socket, model);
                break;
            default:
                break;
        }
    }

    public static void login(Socket socket, ServerComunnicationModel model) {
        System.out.println(model);
        allusers = CrudUser.getAllUsers();
        User user = null;
        for (User u : allusers) {
            if (u.getUsername().trim().equals(model.getMap().get("username")) && u.getPassword().equals(model.getMap().get("password"))) {
                user = u;
            }
        }
        Map<String, String> sendMap = new HashMap<>();
        ServerComunnicationModel sendModel = new ServerComunnicationModel(CommunicationReasonsEnum.LOGIN, sendMap);
        if (user == null) {
            sendMap.put("login", "NULL");
            Server.sendInfoToClient("one", socket, sendModel);
            return;
        }
        sendMap.put("login", user.getGameName());
        sendMap.put("skin", user.getSkinsList().get(0).getSkinImagePath());
        sendMap.put("sessionId", String.valueOf(Server.sessionId));

        Server.sendInfoToClient("one", socket, sendModel);
        Server.sessionId++;
    }

    private static void register(Socket socket, ServerComunnicationModel model) {

        Map<String, Boolean> sendMap = new HashMap<>();
        ServerComunnicationModel sendModel = new ServerComunnicationModel(CommunicationReasonsEnum.REGISTER, sendMap);
        boolean anymatch = false;
        allusers = CrudUser.getAllUsers();
        Map<String, String> map = model.getMap();
        User user = new User(50, map.get("username"), map.get("password"), map.get("email"), map.get("gameName"), 100, Arrays.asList(new Skins(3)), null);
        for (User e : allusers) {
            if (e.getEmail().trim().equals(user.getEmail().trim()) || e.getGameName().trim().equals(user.getGameName().trim())) {
                anymatch = true;
            }
        }
        if (anymatch) {
            sendMap.put("register", false);
            Server.sendInfoToClient("one", socket, sendModel);
        } else {
            sendMap.put("register", true);
            CrudUser.addUser(user);
            Server.sendInfoToClient("one", socket, sendModel);
        }

    }

    private static void move(Socket socket, ServerComunnicationModel model) {
        if (Server.sessionId > 2) {
            checkPause(socket, model);
        }
        Server.sendInfoToClient("many", socket, model);
    }

    private static void changeSkin(Socket socket, ServerComunnicationModel model) {
        allusers = CrudUser.getAllUsers();
        User user = null;
        System.out.println(model.getMap().get("user"));
        for (User u : allusers) {
            if (model.getMap().get("user").equals(u.getGameName())) {
                user = u;
            }
        }

        user.addSkinsToList(new Skins(Integer.valueOf((String) model.getMap().get("userSkin"))));
        CrudUser.updateUser(user);
        Map<String, String> sendMap = new HashMap<>();
        sendMap.put("userSkin", CrudSkin.getSkin(Integer.valueOf((String) model.getMap().get("userSkin"))).getSkinImagePath());
        ServerComunnicationModel sendModel = new ServerComunnicationModel(CommunicationReasonsEnum.CHANGESKIN, sendMap);
        Server.sendInfoToClient("one", socket, sendModel);
    }

    private static void checkPause(Socket socket, ServerComunnicationModel model) {
        Map<String, String> sendMap = new HashMap<>();
        sendMap.put("canStart", "true");
        ServerComunnicationModel sendModel = new ServerComunnicationModel(CommunicationReasonsEnum.CHECKPAUSE, sendMap);
        Server.sendInfoToClient("many", socket, sendModel);
    }

    private static void setScore(Socket socket, ServerComunnicationModel model) {
        System.out.println(model);
        allusers = CrudUser.getAllUsers();
        User user = null;
        System.out.println(model.getMap().get("user"));
        for (User u : allusers) {
            if (model.getMap().get("user").equals(u.getGameName())) {
                user = u;
            }
        }
        user.addScoreToList(new Bestscores(Integer.parseInt((String) model.getMap().get("setScore")), user));
        CrudUser.updateUser(user);
    }

    private static void readScore(Socket socket, ServerComunnicationModel model) {

        List<Bestscores> bestScoreList = CrudBestscores.getAllBestScores();
        Map<String, String> mapOfResults = new HashMap<>();
        for (Bestscores s : bestScoreList) {
            mapOfResults.put(s.getUserId().getUsername(), String.valueOf(s.getScore()));
        }
        Map<String, Map> sendMap = new HashMap<>();
        sendMap.put("scores", mapOfResults);
        ServerComunnicationModel sendModel = new ServerComunnicationModel(CommunicationReasonsEnum.READSCORE, sendMap);
        Server.sendInfoToClient("one", socket, sendModel);
    }

}
