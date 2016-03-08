 //we send our info over to the server side using ajax, nothing
//done on the server side can be seen
var server = 'http://192.168.0.15/Chat/ass5.php';

//lets you register using a user name, and if you dont type one in
//it alerts you need a username
function register(){
    if ($('#username').val()==''){
      alert("You need a username");
      return;
    }
    //if no password is entered, it alerts you need a password
    if ($('#password').val()==''){
      alert("Enter a password dick!");
      return;
    }
    //if the pw equals the confirm pw, 
    //the action register sends to the server your username
    //and password
    if ($('#password').val()==$('#regConf').val()) {
      var dataToSend = {
        action: 'register',
        username: $('#username').val(),
        password: $('#password').val()
    }
    $.post(server,dataToSend,registerResponse);
    
    }else{
        alert("Your passwords don't match");
    }
}
function registerResponse(response){
    $.mobile.navigate('#login');
    alert(response);
    console.log(response);
    }    
function login(){
    if ($('#loginusername').val()==''){
        alert("You need a username");
        return;
    }
    if ($('#loginpassword').val()==''){
        alert("You need a password");
        return;
    }
    //sends to the server ur username and pw if correct
    var dataToSend = {
        action: 'login',
        username: $('#loginusername').val(),
        password: $('#loginpassword').val()
    }
        $.post(server,dataToSend,loginResponse);
}
function loginResponse(response){
    if (response=='success'){
        
        $.mobile.navigate('#message');
        $('#loginusername').val('');
        $('#loginpassword').val('');   
    }
    
}
//this loads the page and checks to see if ur logged in
$(document).ready(checkLogin);

function checkLogin(){
    var dataToSend = {
        action: 'checkLogin'  
    }
    $.post(server,dataToSend,loginResponse);
}
function logout(){
        var dataToSend = {
        action: 'logout'
    }
    $.post(server,dataToSend,logoutResponse);
}
function logoutResponse(response){
    $.mobile.navigate("#login");
}
//this will send the messages to the message box
function send(){
    var dataToSend = {
       action: 'send',
       message: $('#messageBox').val()
    }  
    $('#messageBox').val('');
  
    $.post(server, dataToSend,responseReceived);
}
function responseReceived(response){
  console.log(response);
  $('#messageArea').html(response);
}
//this will get and show the messages
function getMessages(){
    var dataToSend = {
        action: 'show'
    }
    $.post(server,dataToSend,responseReceived);
}
//will check for messages every second
setInterval(getMessages,1000);

//if the keycode is equal to 13, it will send
$(document).ready(function(){
    $('#message').keyup(function(ev){
        if (ev.keyCode==13) {
            send();
        }
    })
});
$(document).on( "pagecontainershow", function( event, ui ) {
    var hWindow = $(window).height();
    var hButtons = $('#messageButtons').height();
    var cont = $('#message > [data-role="content"]');
    
    console.log($('#message > [data-role="content"]').css('padding'))
     $('div#scrollArea').css({height: $('#message').height() - $('#messageButtons').height()});
} );