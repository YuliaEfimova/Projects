package org.example.untitled;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyAmazingBot extends TelegramLongPollingBot {
    private boolean authorisation = false;
    private ArrayList<String> data = new ArrayList<>();

    public void setAuthorisation(boolean authorisation) {
        this.authorisation = authorisation;
    }

    public void setdata(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            Message message = update.getMessage();

            try {
                String command = update.getMessage().getText();

                switch (command) {
                    case "/head":
                    case "Главная":
                        System.out.println("Вы вернулись на главную страницу");
                        sendMsg(message, "Вы вернулись на главную страницу", 1);
                        break;
                    case "/options":
                    case "Функционал":
                        System.out.println("Вы хотите узнать мой функционал?");
                        sendMsg(message, "Вы хотите узнать мой функционал? Я пока еще совсем маленький " +
                                "бот и умею не многое, например посмотреть Ваш больничный лист, узнать, " +
                                "когда Вам пора на работу,и сколько больничных листов у Вас", 1);
                        break;
                    case "/help":
                    case "Помощь":
                        System.out.println("Чем я могу помочь?");
                        sendMsg(message, "Ты большой и умный человек, а я маленький ботик, не думаю," +
                                " что смогу помочь тебе лучше, чем ты сам", 1);
                        break;
                    case "/profile":
                    case "Профиль":
                        System.out.println("Перехожу к вашему профилю...");
                        sendMsg(message, "Перехожу к вашему профилю...", 1);
                        sendMsg(message, doBottom("profile"), 1);

                        break;
                    case "/authorisation":
                    case "Авторизация":
                        System.out.println("Авторизация...");
                        sendMsg(message, "Вы уже зарегистрировались?", 4);
                        break;
                    case "Да. Хочу ввести СНИЛС":
                        System.out.println("Да. Хочу ввести СНИЛС");
                        sendMsg(message, "Введите СНИЛС", 3);
                        setAuthorisation(true);
                        break;
                    case "Нет. Хочу зарегистрироваться":
                        System.out.println("Нет. Хочу зарегистрироваться");
                        sendMsg(message, "Перейдите на сайт http://localhost:8080/login", 3);
                        break;
                    case "/menu":
                    case "Меню":
                        System.out.println("Выберите одну из следующих опций: ");
                        sendMsg(message, "Выберите одну из следующих опций: " + "\n" + "/ill" + " - оформить больничный лист" + "\n"
                                + "/info" + " - получить информацию" + "\n" + "/time"
                                + "- узнать время, когда в последний раз обращались в больницу" + "\n" + "/share"
                                + " - отправить данные на почту", 2);
                        break;
                    case "/ill":
                    case "Больничный лист":
                        System.out.println("Оформляю больничный лист");
                        sendMsg(message, "Оформляю больничный лист сегодняшней датой", 3);
                        sendMsg(message, doBottom("ill"), 3);
                        break;
                    case "/info":
                    case "Информация":
                        System.out.println("Данные, СНИЛС, кол-во больничных");
                        sendMsg(message, "Данные, СНИЛС, кол-во больничных", 3);
                        sendMsg(message, doBottom("info"), 3);
                        break;
                    case "/time":
                    case "Последнее посещение":
                        System.out.println("Время, когда в последний раз обращались в больницу: ");
                        sendMsg(message, "Время, когда в последний раз обращались в больницу: ", 3);
                        sendMsg(message, doBottom("time"), 3);
                        break;
                    case "/share":
                    case "Поделиться":
                        System.out.println("Отправить данные на почту другому пользователю?");
                        sendMsg(message, "Отправить данные на почту другому пользователю?", 3);
                        sendMsg(message, doBottom("share"), 3);
                        break;
                    default:
                        if (authorisation) {
                            sendMsg(message, login(message.getText()), 3);
                        }

                        break;
                }
            } catch (TelegramApiException e) {
                System.out.println(e);
            }
        }
    }

    public String login (String username) {
        try{
            String name = "yulia";
            String url = "jdbc:postgresql://localhost:5432/mipt_sif";
            String pass = "poilvik117";
            Connection conn = DriverManager.getConnection(url, name, pass);
            Statement statement = conn.createStatement();
            String s = "";
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mipt");

            ArrayList<String> user = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String userFirstName = resultSet.getString("first_name");
                String userSecondName = resultSet.getString("second_name");
                String userMiddleName = resultSet.getString("middle_name");
                String usrName = resultSet.getString("username");
                String sickDate = resultSet.getString("sick_date");
                Integer sickPeriod = resultSet.getInt("sick_period");
                Integer closedSheets = resultSet.getInt("closed_sheets");
                String dateOfBirth = resultSet.getString("date_of_birth");

                System.out.println(id + ". " + userFirstName + " - " + usrName);
                if(usrName.equals(username)) {
                    System.out.println(userFirstName);
                    s = userFirstName;
                    user.add(String.valueOf(id));
                    user.add(userFirstName);
                    user.add(userSecondName);
                    user.add(userMiddleName);
                    user.add(usrName);
                    user.add(sickDate);
                    user.add(String.valueOf(sickPeriod));
                    user.add(String.valueOf(closedSheets));
                    user.add(dateOfBirth);

                    setdata(user);
                    return "Здравствуйте, " + s;
                }
            }
            return "Кажется вы не зарегистрированы";
        } catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return "Что-то пошло не так";
        }
    }

    public String doBottom(String bottom) {
        switch (bottom) {
            case "profile":
                return "Вас зовут " + data.get(1) + " " + data.get(2) + " " + data.get(3) + ". " + "\n" +
                        "Вы родились: " + data.get(8) + ". " + "\n" +
                        "Ваш номер СНИЛС: " + data.get(4) + ". " + "\n" +
                        "У вас " + data.get(7) + " закрытых больничных листов" + ". " + "\n";
            case "ill":
                data.set(5, "Сегодня");
                data.set(6, "0");
                return null;
            case "info":
                String SD = "Последний раз Вы  открыли больничный: " + data.get(5);
                if (data.get(5).equals("0")) {
                    SD = "Данные о Вашем больничном еще не занесены";
                }
                String SP = "Последний раз Вы  открыли больничный: " + data.get(6) + " дней назад";
                if (data.get(6).equals("0")) {
                    SP = "Вы еще не на больничном";
                }
                return "Ваш номер СНИЛС: " + data.get(4) + ". " + "\n" +
                        SD + ". " + "\n" +
                        SP + ". " + "\n" +
                        "У вас " + data.get(7) + " закрытых больничных листов" + ". " + "\n";
            case "time":
                return "Последний раз Вы  открыли больничный: " + data.get(5);
        }
        return null;
    }

    public void sendMsg(Message message, String s, int number) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);
        setButtonsStart(sendMessage, number);
        execute(sendMessage);
    }

    public synchronized void setButtonsStart(SendMessage sendMessage, int number) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        if (number == 1) {
            keyboardFirstRow.add(new KeyboardButton("Профиль"));
            keyboardFirstRow.add(new KeyboardButton("Меню"));
            keyboardSecondRow.add(new KeyboardButton("Авторизация"));
            keyboardSecondRow.add(new KeyboardButton("Функционал"));
        } else if (number == 2) {
            keyboardFirstRow.add(new KeyboardButton("Больничный лист"));
            keyboardFirstRow.add(new KeyboardButton("Информация"));
            keyboardSecondRow.add(new KeyboardButton("Последнее посещение"));
            keyboardSecondRow.add(new KeyboardButton("Поделиться"));
        } else if (number == 3) {
            keyboardFirstRow.add(new KeyboardButton("Главная"));
            keyboardFirstRow.add(new KeyboardButton("Меню"));
        } else if (number == 4) {
            keyboardFirstRow.add(new KeyboardButton("Да. Хочу ввести СНИЛС"));
            keyboardFirstRow.add(new KeyboardButton("Нет. Хочу зарегистрироваться"));
        }
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public String getBotUsername() {
        return "MIPT_SIF";
    }

    @Override
    public String getBotToken() {
        return "1758468047:AAEgoD3vAyl5MwfWZ5n6y2zBLOdSTcKZRJU";
    }
}
