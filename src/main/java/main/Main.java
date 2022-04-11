package main;

import CustomExceptions.OutOfRangeInput;
import entity.Account;
import entity.Comment;
import entity.Reply;
import entity.Twit;
import service.impl.AccountServiceImpl;
import service.impl.CommentServiceImpl;
import service.impl.ReplyServiceImpl;
import service.impl.TwitServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static AccountServiceImpl accountService = new AccountServiceImpl();
    static TwitServiceImpl twitService = new TwitServiceImpl();
    static CommentServiceImpl commentService = new CommentServiceImpl();
    static ReplyServiceImpl replyService = new ReplyServiceImpl();

    public static void main(String[] args) {
        Boolean flag = true;
        while (flag) {
            System.out.println("1-login" + "\n" + "2-singUp" + "\n" + "3-exit");
            try {
                Integer operator = input.nextInt();
                if (operator > 3 || operator < 1) {
                    throw new OutOfRangeInput("please enter something in range!");
                }
                switch (operator) {
                    case 1:
                        login();
                        break;
                    case 2:
                        singUp();
                        break;
                    case 3:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            } catch (OutOfRangeInput e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void login() {
        System.out.println("please enter your username:");
        String username = input.next();
        System.out.println("please enter your password:");
        String password = input.next();
        Account account = new Account();
        account.setUserName(username);
        account.setPassword(password);
        Integer id = accountService.login(account);
        if (id <= 0) {
            System.out.println("there is no acc with this username and password!");
            return;
        } else {
            loggedInAccount(id);
        }
    }

    public static void singUp() {
        System.out.println("please enter your firstname:");
        String firstname = input.next();
        System.out.println("please enter your lastname:");
        String lastname = input.next();
        System.out.println("please enter your username:");
        String username = input.next();
        System.out.println("please enter your password:");
        String password = input.next();
        Account account = new Account(firstname, lastname, username, password, null, null, null);
        if (accountService.create(account) > 0) {
            System.out.println("done!");
            return;
        }
        System.out.println("something went wrong! try again!");
    }

    public static void loggedInAccount(Integer id) {
        Account account = accountService.findById(id);
        System.out.println("welcome " + account.getFirstName() + "!");
        boolean flag = true;
        while (flag) {
            System.out.println("1-making a new twit" + "\n" +
                    "2-editing your twits" + "\n" +
                    "3-deleting your twits" + "\n" +
                    "4-showing all twits" + "\n" +
                    "5-searching accounts" + "\n" +
                    "6-editing your profile" + "\n" +
                    "7-deleting your profile" + "\n" +
                    "8-showing your followers" + "\n" +
                    "9-exit");
            try {
                Integer operator = input.nextInt();
                if (operator > 9 || operator < 1) {
                    throw new OutOfRangeInput("please enter something in range!");
                }
                switch (operator) {
                    case 1:
                        addingNewTwit(account);
                        break;
                    case 2:
                        editingTwit(account);
                        break;
                    case 3:
                        deletingTwit(account);
                        break;
                    case 4:
                        showingAllTwits(account);
                        break;
                    case 5:
                        searchingAccounts(account);
                        break;
                    case 6:
                        editingProfile(account);
                        break;
                    case 7:
                        deletingProfile(account);
                        break;
                    case 8:
                        showingFollowers(account);
                        break;
                    case 9:
                        flag = false;
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            } catch (OutOfRangeInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addingNewTwit(Account account) {
        System.out.println("please enter the text of your twit(enter means its finished and max 280 character):");
        String content = input.next();
        if (content.length() > 280) {
            System.out.println("two much character! please try again!");
        } else {
            Twit twit = new Twit();
            twit.setAccount(account);
            twit.setContent(content);
            twit.setLikes(0);
            if (twitService.create(twit) > 0) {
                System.out.println("done!");
                return;
            } else {
                System.out.println("something went wrong!please try again!");
            }
        }
    }

    public static void editingTwit(Account account) {
        account
                .getTwits()
                .stream()
                .forEach(System.out::println);
        System.out.println("please enter id of the twit you want to edit:");
        try {
            Integer twitId = input.nextInt();
            System.out.println("please enter a new value for content:");
            String newContent = input.next();
            if (newContent.length() > 280) {
                System.out.println("two much character! please try again!");
            } else {
                Twit twit = twitService.findById(twitId);
                twit.setContent(newContent);
                twitService.Update(twit);
            }
        } catch (InputMismatchException e) {
            System.out.println("please enter a number!");
        }

    }

    public static void deletingTwit(Account account) {
        account
                .getTwits()
                .stream()
                .forEach(System.out::println);
        System.out.println("please enter id of the twit you want to edit:");
        try {
            Integer twitId = input.nextInt();
            Twit twit = twitService.findById(twitId);
            if (twit != null) {
                twitService.Delete(twitId);
            }
        } catch (InputMismatchException e) {
            System.out.println("please enter a number!");
        }
    }

    public static void showingAllTwits(Account account) {
        boolean flag = true;
        while (flag) {
            List<Twit> twits = twitService.findAll();
            twits
                    .stream()
                    .forEach(System.out::println);
            System.out.println("enter twit id for other things(liking,commenting):");
            try {
                Integer twitId = input.nextInt();
                Twit twit = twitService.findById(twitId);
                if (twit != null) {
                    workingOnTwit(account, twit);
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            }
        }
    }

    public static void searchingAccounts(Account account) {
        System.out.println("please enter username you want to see:");
        String username = input.next();
        Account account1 = accountService.findByUsername(username);
        if (account1 != null) {
            workingOnAccount(account, account1);
        }
    }

    public static void editingProfile(Account account) {
        System.out.println("1-edit your firstname" + "\n" +
                "2-edit your lastname" + "\n" +
                "3-edit your username" + "\n" +
                "4-edit your password");
        try {
            Integer operator = input.nextInt();
            if (operator > 4 || operator < 1) {
                throw new OutOfRangeInput("please enter something in range!");
            }
            System.out.println("enter new value:");
            switch (operator) {
                case 1:
                    account.setFirstName(input.next());
                    accountService.Update(account);
                    break;
                case 2:
                    account.setLastName(input.next());
                    accountService.Update(account);
                    break;
                case 3:
                    account.setUserName(input.next());
                    accountService.Update(account);
                    break;
                case 4:
                    account.setPassword(input.next());
                    accountService.Update(account);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("please enter a number!");
        } catch (OutOfRangeInput e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletingProfile(Account account) {
        accountService.Delete(account.getId());
    }

    public static void showingFollowers(Account account) {
        account
                .getFollower()
                .stream()
                .forEach(System.out::println);
    }

    public static void workingOnTwit(Account account, Twit twit) {
        boolean flag = true;
        while (flag) {
            System.out.println("1-liking twit" + "\n" +
                    "2-disliking twit" + "\n" +
                    "3-adding a comment to twit" + "\n" +
                    "4-adding a reply to comments" + "\n" +
                    "5-exit");
            try {
                Integer operator = input.nextInt();
                if (operator > 5 || operator < 1) {
                    throw new OutOfRangeInput("please enter something in range!");
                }
                switch (operator) {
                    case 1:
                        twit.setLikes(twit.getLikes() + 1);
                        twitService.Update(twit);
                        break;
                    case 2:
                        twit.setDisLikes(twit.getDisLikes() + 1);
                        twitService.Update(twit);
                        break;
                    case 3:
                        System.out.println("enter your comment:");
                        String comment = input.next();
                        Comment comment1 = new Comment();
                        comment1.setContent(comment);
                        comment1.setAccount(account);
                        comment1.setTwit(twit);
                        commentService.create(comment1);
                        break;
                    case 4:
                        twit
                                .getCommentSet()
                                .stream()
                                .forEach(System.out::println);
                        System.out.println("please enter id of the comment that you want to reply:");
                        try {
                            Integer commentId = input.nextInt();
                            Comment comment2 = commentService.findById(commentId);
                            if (comment2 != null) {
                                System.out.println("please enter your reply(280 character maximum):");
                                String reply = input.next();
                                if (reply.length() > 0) {
                                    Reply reply1 = new Reply();
                                    reply1.setAccount(account);
                                    reply1.setComment(comment2);
                                    reply1.setContent(reply);
                                    replyService.create(reply1);
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("please enter a number!");
                        }
                        break;
                    case 5:
                        flag = true;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            } catch (OutOfRangeInput e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void workingOnAccount(Account viewerAccount, Account viewingAccount) {
        Boolean flag = true;
        while (flag) {
            System.out.println("1-follow" + "\n" + "2-unfollow" + "\n" + "3-exit");
            try {
                Integer operator = input.nextInt();
                if (operator > 3 || operator < 1) {
                    throw new OutOfRangeInput("please enter something in range!");
                }
                switch (operator) {
                    case 1:
                        if (viewingAccount.getFollower().contains(viewerAccount)) {
                            System.out.println("you already followed this account!");
                        } else {
                            viewingAccount.getFollower().add(viewerAccount);
                            viewerAccount.getFollowing().add(viewingAccount);
                            accountService.Update(viewingAccount);
                            accountService.Update(viewerAccount);
                        }
                        break;
                    case 2:
                        if (!viewingAccount.getFollower().contains(viewerAccount)) {
                            System.out.println("you already unfollowed this account!");
                        } else {
                            viewingAccount.getFollower().remove(viewerAccount);
                            viewerAccount.getFollowing().remove(viewingAccount);
                            accountService.Update(viewingAccount);
                            accountService.Update(viewerAccount);
                        }
                        break;
                    case 3:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("please enter a number!");
            } catch (OutOfRangeInput e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
