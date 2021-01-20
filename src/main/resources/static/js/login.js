
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
  // Send to create function
  checkLogin(data); 

  // Send to createItem with params
  //window.location.replace("album.html?id="+trackData.album.id);

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
    
      // Send to createItem with params
      //window.location.replace("album.html?id="+trackData.album.id);
    
  }
  
});

// Checks if login details work
function checkLogin(dataIn){
    fetch("http://localhost:8082/users/login", {
        method: 'post',
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        },
        body:JSON.stringify(dataIn)
      })
      .then(function (data) {
          // Logs in and adds cookie
          login(dataIn.username)
            console.log('Success! Response: ', data.body);
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

                let date = new Date();
                date.setTime(date.getTime() + (1*24*60*60*1000));
                let expires = "expires="+ date.toUTCString();
                document.cookie = "userid=" + "=" + data.id + ";" + expires;
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
          checkLogin(dataIn)
       
      //console.log(dataIn)
          
        console.log('Success! Response: ', data.body);
      })
      .catch(function (error) {
        console.log('Failed! Response: ', error);
      });
}

