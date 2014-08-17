<!DOCTYPE html>
<% 	String name = session.getAttribute("name").toString();
	String title = session.getAttribute("title").toString();
%>
<html>
  <head>
    <title><%= title %></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css' />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/style/font-awesome.min.css' />
    <link rel='stylesheet' href='${pageContext.request.contextPath}/style/style.css' />
    <link rel='shortcut icon' type='image/x-icon' href='/favicon.ico' />
  </head>
  <body>
      
    <div class='container-fluid'>
        
        <header id='head-nav' class='row primary-bg-color'>
            
            <div id='head-cobra-ki' class='col-xs-12 col-md-4 col-md-push-4 primary-bg-color'>
            
                <h1 class='secondary-font-color'>Cobra Chat for <%= name %> </h1>
            
            </div>            
            
            <div id='head-home-icon' class='col-xs-3 col-md-4 col-md-pull-4 primary-bg-color'>
                
                <a href='#'>
                    
                    <i class='fa fa-home fa-4x primary-font-color'></i>
                    
                </a>
                
            </div>
                  
            <div id='head-cog-icon' class='col-xs-9 col-md-4 primary-bg-color'>
            
            
                <a href='#'>
                    
                    <i class='fa fa-power-off fa-4x pull-right primary-font-color'></i>
                    
                </a>
                
                <a href='#'>
                    
                    <i class='fa fa-cog fa-4x primary-font-color'></i>
                
                </a>
                    
            
            </div>

        </header>
        
            <div id='row-body' class="row">
                
                <section id='section-middle' class='col-xs-12 col-md-8 col-md-push-2'>
                    
                    <div id='row-section-middle' class='row'>
                        
                        <section id='chatMessages' class='col-xs-12'>
                            
                        </section>                        
                            
                        <div id='chatTextarea' class='col-xs-12'>
                                
                            <textarea id='messageInput' class='col-xs-12'></textarea>
                            <input type="hidden" id="displayName" value="<%= name %>" >
                        </div>
                        
                    </div>
                    
                </section>
                
                <nav id='left-nav' class='col-xs-12 col-md-2 col-md-pull-8'>
                    
                    <div id='left-nav-row' class='row'>
                        
                        <div id='left-nav-content' class='col-xs-12 primary-bg-color'>
                            
                            <a href='#'>
                                
                                <i class='fa fa-users fa-5x primary-font-color'></i>
                                
                            </a>
                            
                        </div>
                        
                    </div>
                    
                </nav>
            
                
                
                <aside id='aside-right' class='col-xs-12 col-md-2'>
                    
                    <div id='right-aside-row' class='row'>
                        
                        <div id='right-aside-content' class='col-xs-12 primary-bg-color'>
                                                    
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
    	
    <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
    <!--<script src='/socket.io/socket.io.js'></script>
    <script src='/scripts/socketScript.js'></script>-->
    
  </body>
</html>