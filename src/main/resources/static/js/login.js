document.cookie = "userid=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

document
.querySelector("form.logInForm")
.addEventListener("submit", function (stop) {
  stop.preventDefault();
  let formElements = document.querySelector("form.logInForm").elements;

  let username=formElements["logInName"].value;
  let password=formElements["logInPassword"].value;
  // JSON object for data
  let data = {

    "username":username,
    "password":password

  }

  checkLogin(username,password); 



});


document
.querySelector("form.signUpForm")
.addEventListener("submit", function (stop) {
  stop.preventDefault();
  let formElements = document.querySelector("form.signUpForm").elements;

  let username=formElements["signUpName"].value;
  let password1=formElements["signUpPassword1"].value;
  let password2=formElements["signUpPassword2"].value;

  // JSON object for data
  if(password1==password2){
    let data = {

        "username":username,
        "password":password1
    
      }

      // Send to create function
      register(data); 

     // window.location.replace("index.html");
    
  }
  else{
    alert("The passwords do not match, try again");
  }
  
});

// Checks if login details work
function checkLogin(username,password){
    fetch("http://localhost:8082/users/login/"+username+"/"+password)
      .then(function (response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
                response.status);
            return;
        }
        response.json().then(function (data) {
          // Logs in and adds cookie
          if(data==true){
            login(username)
            console.log('Success! Response: ', data.body);
          }
          else{
            alert("Incorrect Credentials, try again.");
          }
        
               
            });

      })
      .catch(function (error) {
        console.log('Failed! Response: ', error);
      });
}

// Gets user id and adds cookie
function login(username){
    fetch('http://localhost:8082/users/find/' + username)
    .then(
        function (response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }

            // Examine the text in the response
            response.json().then(function (data) {
            if(data.id>0){
                let date = new Date();
                date.setTime(date.getTime() + (1*24*60*60*1000));
                let expires = "expires="+ date.toUTCString();
                document.cookie = "userid=" + data.id + ";" + expires;
                document.cookie = "name=" + data.username + ";" + expires;
                window.location.replace("index.html");
            }
               
            });
        }
    )
    .catch(function (err) {
        console.log('Fetch Error :-S', err);
    });

}

// Creates a user
function register(dataIn){
    fetch("http://localhost:8082/users/create", {
        method: 'post',
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        },
        body:JSON.stringify(dataIn)
      })
      .then(function (data) {
         // console.log(data);
          checkLogin(dataIn.username, dataIn.password)
       
      //console.log(dataIn)
          
        console.log('Success! Response: ', data.body);
      })
      .catch(function (error) {
        console.log('Failed! Response: ', error);
      });
}

