Handlebars provides the power necessary to let you build semantic templates effectively with no frustration.


<!DOCTYPE html>
<html>
  <head>
    <title>Handlebars Expressions Example</title>
  </head>
  <body>
    <h1>Handlebars Expressions Example!</h1>
<!--this is a list which will rendered by handlebars template.    --> 
    <div id="list">
    </div>
    
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/handlebars-1.0.0.beta.6.js"></script>
    
    <script id="people-template" type="text/x-handlebars-template">
      {{#people}}
        <div class="person">
          <h2>{{first_name}} {{last_name}}</h2>
          <div class="phone">{{phone}}</div>
          <div class="email"><a href="mailto:{{email}}">{{email}}</a></div>
          <div class="since">User since {{member_since}}</div>
        </div>
      {{/people}}
    </script>
    
    <script type="text/javascript">
      $(document).ready(function() {
        
        // compile our template
        var template = Handlebars.compile($("#people-template").html());
        
        var data = {
          people: [
            { first_name: "Alan", last_name: "Johnson", phone: "1234567890", email: "alan@test.com", member_since: "Mar 25, 2011" },
            { first_name: "Allison", last_name: "House", phone: "0987654321", email: "allison@test.com", member_since: "Jan 13, 2011" },
            { first_name: "Nick", last_name: "Pettit", phone: "9836592272", email: "nick@test.com", member_since: "Apr 9, 2009" },
            { first_name: "Jim", last_name: "Hoskins", phone: "7284927150", email: "jim@test.com", member_since: "May 21, 2010" },
            { first_name: "Ryan", last_name: "Carson", phone: "8263729224", email: "ryan@test.com", member_since: "Nov 1, 2008" }
          ]
        };
        
        $('#list').html(template(data));
      });
    </script>
  </body>
</html>
