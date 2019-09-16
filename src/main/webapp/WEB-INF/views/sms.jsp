
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compose email</title>
    </head>
    <style>
        * {
            box-sizing: border-box;
        }


        body {
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
        }


        .header h1 {
            font-size: 40px;
        }

        /* Sticky navbar - toggles between relative and fixed, depending on the scroll position. It is positioned relative until a given offset position is met in the viewport - then it "sticks" in place (like position:fixed). The sticky value is not supported in IE or Edge 15 and earlier versions. However, for these versions the navbar will inherit default position */
        .navbar {
            overflow: hidden;
            background-color: #b0d5d6;
            position: sticky;
            position: -webkit-sticky;
            top: 0;
        }

        /* Style the navigation bar links */
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }


        /* Right-aligned link */
        .navbar a.right {
            float: right;
        }

        /* Change color on hover */
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Active/current link */
        .navbar a.active {
            background-color: #665;
            color: white;
        }

        /* Column container */
        .row {  
            display: -ms-flexbox; /* IE10 */
            display: flex;
            -ms-flex-wrap: wrap; /* IE10 */
            flex-wrap: wrap;
        }

        /* Create two unequal columns that sits next to each other */
        /* Sidebar/left column */
        .side {
            -ms-flex: 30%; /* IE10 */
            flex: 30%;
            background-color: #f1f1f1;
            padding: 20px;
        }

        /* Main column */
        .main {   
            -ms-flex: 70%; /* IE10 */
            flex: 70%;
            background-color: white;
            padding: 20px;
        }

        /* Fake image, just for this example */
        .fakeimg-center {
            width: 100%;
            padding: 20px;
            display: block;
        }
        .fakeimg {
            background-color: white;
            width: 100%;
            padding: 20px;

        }
        .div1 {
            text-align: center;
            color: black
        }

        .div1 a{
            font-size: 30px;
            color: red;
            font-weight: bold;

        }

        /* Footer */

        .p{
            text-align: justify;
        }
        /* Responsive layout - when the screen is less than 700px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 700px) {
            .row {   
                flex-direction: column;
            }
        }

        /* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
        @media screen and (max-width: 400px) {
            .navbar a {
                float: none;
                width: 100%;
            }
        }



        .header {
            padding: 80px;
            text-align: center;
            background: #4cbff5;
            color: black;
        }
        .footer-distributed{
            background-color: #292c2f;
            box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.12);
            box-sizing: border-box;
            width: 100%;
            font: bold 16px sans-serif;
            text-align: left;

            padding: 50px 60px 40px;
            margin-top: 80px;
            overflow: hidden;
        }

        /* Footer left */

        .footer-distributed .footer-left{
            float: left;
        }

        /* The company logo */

        .footer-distributed h3{
            color:  #ffffff;
            font: normal 36px 'Cookie', cursive;
            margin: 0 0 10px;
        }

        .footer-distributed h3 span{
            color:  #5383d3;
        }

        /* Footer links */

        .footer-distributed .footer-links{
            color:  #ffffff;
            margin: 0 0 10px;
            padding: 0;
        }

        .footer-distributed .footer-links a{
            display:inline-block;
            line-height: 1.8;
            text-decoration: none;
            color:  inherit;
        }

        .footer-distributed .footer-company-name{
            color:  #8f9296;
            font-size: 14px;
            font-weight: normal;
            margin: 0;
        }

        /* Footer social icons */

        .footer-distributed .footer-icons{
            margin-top: 40px;
        }

        .footer-distributed .footer-icons a{
            display: inline-block;
            width: 35px;
            height: 35px;
            cursor: pointer;
            background-color:  #33383b;
            border-radius: 2px;

            font-size: 20px;
            color: #ffffff;
            text-align: center;
            line-height: 35px;

            margin-right: 3px;
            margin-bottom: 5px;
        }

        /* Footer Right */

        .footer-distributed .footer-right{
            float: right;
        }

        .footer-distributed .footer-right p{
            display: inline-block;
            vertical-align: top;
            margin: 15px 42px 0 0;
            color: #ffffff;
        }

        /* The contact form */

        .footer-distributed form{
            display: inline-block;
        }

        .footer-distributed form input,
        .footer-distributed form textarea{
            display: block;
            border-radius: 3px;
            box-sizing: border-box;
            background-color:  #1f2022;
            box-shadow: 0 1px 0 0 rgba(255, 255, 255, 0.1);
            border: none;
            resize: none;

            font: inherit;
            font-size: 14px;
            font-weight: normal;
            color:  #d1d2d2;

            width: 400px;
            padding: 18px;
        }

        .footer-distributed ::-webkit-input-placeholder {
            color:  #5c666b;
        }

        .footer-distributed ::-moz-placeholder {
            color:  #5c666b;
            opacity: 1;
        }

        .footer-distributed :-ms-input-placeholder{
            color:  #5c666b;
        }


        .footer-distributed form input{
            height: 55px;
            margin-bottom: 15px;
        }

        .footer-distributed form textarea{
            height: 100px;
            margin-bottom: 20px;
        }

        .footer-distributed form button{
            border-radius: 3px;
            background-color:  #33383b;
            color: #ffffff;
            border: 0;
            padding: 15px 50px;
            font-weight: bold;
            float: right;
        }

        /* If you don't want the footer to be responsive, remove these media queries */

        @media (max-width: 1000px) {

            .footer-distributed {
                font: bold 14px sans-serif;
            }

            .footer-distributed .footer-company-name{
                font-size: 12px;
            }

            .footer-distributed form input,
            .footer-distributed form textarea{
                width: 250px;
            }

            .footer-distributed form button{
                padding: 10px 35px;
            }

        }

        @media (max-width: 800px) {

            .footer-distributed{
                padding: 30px;
            }

            .footer-distributed .footer-left,
            .footer-distributed .footer-right{
                float: none;
                max-width: 300px;
                margin: 0 auto;
            }

            .footer-distributed .footer-left{
                margin-bottom: 40px;
            }

            .footer-distributed form{
                margin-top: 30px;
            }

            .footer-distributed form{
                display: block;
            }

            .footer-distributed form button{
                float: none;
            }
        }

        .table {
            display: table;


        }

        .row {
            display: table-row;
        }

        .cell {
            display: table-cell;
            padding: 10px;
        }

        .row:hover {
            background-color: #cccccc;
        }

        .cell:hover {
            background-color: #e5e5e5;
        }

        td {border: 1px #DDD solid; padding: 5px; cursor: pointer;}
        body {
            font-family: Arial, Helvetica, sans-serif;
            margin: 0;
        }



    </style>
    <body>

        <div class="navbar">
            <a href="/MsgNST/email" class="active">Email</a>
            <a href="/MsgNST/sms" class="active">SMS message</a>
            <a href="/MsgNST/" class="right">Logout</a>
        </div>
        <form  action="messageProcess" method="post" >
            <table>

                <tr>

                </tr>

                <tr>
                    <td><b>To:</b></td>
                    <td>
                        <input type="text" name = "to" size ="75" >
                    </td>

                </tr>
                <tr>
                    <td><b>Message: </b></td>
                    <td>
                        <textarea name = "body"  cols ="75" rows ="6" ></textarea>
                    </td>

                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class = "submitclass" type="submit" value="Send">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
