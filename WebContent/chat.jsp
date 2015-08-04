<!DOCTYPE html>
<%@ page import="org.ryyaan2004.chat.util.ChatProperties" %>
<%@ page import="org.ryyaan2004.chat.util.Constants" %>
<% 	
	ChatProperties props = ChatProperties.getInstance();
    String callbackUri = props.getOauthValue(Constants.CALLBACK_URI);
    
    String name = session.getAttribute("name").toString();
	String title = session.getAttribute("title").toString();
	String protocol = callbackUri.contains("localhost") ? "ws://" : "wss://";
	String socketUri = protocol + (callbackUri.contains("localhost") ? "localhost:8080/chat/nest" : "ryyaan2004.org/chat/nest");
	
%>
<html>
  <head>
    <title><%= title %></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css' />
    <%--<link rel='stylesheet' href='${pageContext.request.contextPath}/style/font-awesome.min.css' /> --%>
    <%--<link rel='stylesheet' href='${pageContext.request.contextPath}/style/style.css' /> --%>
    <link rel='stylesheet' href='style/font-awesome.min.css' />
    <link rel='stylesheet' href='style/styles.css' />
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico' />
    <script type="text/javascript">
          userName = "<%= name %>";
          user_file = '';
    </script>
    <script type="text/javascript">
	function openSocket(socket){
        // Ensures only one connection is open at a time
        if( typeof socket == 'WebSocket' && socket.readyState !== WebSocket.CLOSED){
           writeResponse("WebSocket is already opened.");
            return;
        }
        // Create a new instance of the websocket
        return socket = new WebSocket("<%= socketUri %>");
	}
    </script>
  </head>
  <body>
      
    <body>
    <div class='container-fluid'>
        <header class='head-nav row primary-bg-color'>
            <div class='head-cobra-ki col-xs-12 col-md-4 col-md-push-4 primary-bg-color'>
                <h1 class='secondary-font-color'>Cobra Chat <%= name %> </h1>
            </div>
            <div class='col-xs-3 col-md-4 col-md-pull-4 primary-bg-color'>
                <a href='#'>
                    <i class='fa fa-home fa-4x primary-font-color'></i>
                </a>
            </div>
            <div class='col-xs-9 col-md-4 primary-bg-color'>
                <a href='#'>
                    <i class='fa fa-power-off fa-4x pull-right primary-font-color'></i>
                </a>
                <a href='#'>
                    <i class='fa fa-cog fa-4x primary-font-color'></i>
                </a>
            </div>
        </header>
            <div class="row-body row">
                <section class='section-middle col-xs-12 col-md-8 col-md-push-2'>
                    <div class='row-section-middle row'>
                        <section id='chatMessage' class='chatMessages col-xs-12'>
                        </section>
                        <div class='chatTextarea col-xs-12'>
                            <textarea id='messageInput' class='messageInputs col-xs-12'></textarea>
                            <input type="hidden" id="displayName" value="<%= name %>" >
                        </div>
                        <div>
                            <input id="image_for_message" type="file" accept="image/*" />
                        </div>
                    </div>
                </section>
                <nav class='col-xs-12 col-md-2 col-md-pull-8 left-nav'>
                    <div class='left-nav-row row'>
                        <div id="left-nav-content" class='left-nav-contents col-xs-12 primary-bg-color'>
                            <a href='#'>
                                <i class='fa fa-users fa-5x primary-font-color'></i>
                            </a>
                            <div id="users"></div>
                        </div>
                    </div>
                </nav>
                <aside class='aside-right col-xs-12 col-md-2'>
                    <div class='right-aside-row row'>
                        <div class='right-aside-content col-xs-12 primary-bg-color'>
                            <a href='#'>
                                <i class='fa fa-folder fa-5x primary-font-color'></i>
                            </a>
                        </div>
                    </div>
                </aside>
            </div>
        <footer>
        </footer>
    </div>
    	
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    
    <script data-main="script/socketScript.js" src="script/require.js"></script>
    <!--<script src="script/socketScript.js"></script>-->
    <!--<script src='/socket.io/socket.io.js'></script>
    <script src='/scripts/socketScript.js'></script>
    
    <script type="text/javascript">
                       
            var webSocket;
            var messages = document.getElementById("chatMessages");
           
           
            function openSocket(){
                // Ensures only one connection is open at a time
                if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                   writeResponse("WebSocket is already opened.");
                    return;
                }
                // Create a new instance of the websocket
                webSocket = new WebSocket("ws://localhost:8080/chat/nest");
                 
                /**
                 * Binds functions to the listeners for the websocket.
                 */
                webSocket.onopen = function(event){
                    // For reasons I can't determine, onopen gets called twice
                    // and the first time event.data is undefined.
                    // Leave a comment if you know the answer.
                    if(event.data === undefined)
                        return;
 
                    writeResponse(event.data);
                };
 
                webSocket.onmessage = function(event){
                    writeResponse(event.data);
                };
 
                webSocket.onclose = function(event){
                    writeResponse("Connection closed");
                };
            }
           
            /**
             * Sends the value of the text input to the server
             */
            function send(){
                var text = document.getElementById("messageInput").value;
                webSocket.send(text);
            }
           
            function closeSocket(){
                socket.close();
            }
 
            function writeResponse(text){
                messages.innerHTML += "<br/>" + text;
            }
           
        </script> -->
    
  </body>
</html>