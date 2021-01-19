function getAlbum(id) {
    fetch('http://localhost:8082/tracks/read/' + id)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function (trackData) {
                    console.log(trackData)
                    console.log(trackData.album.genre.name)
                    let title = document.getElementById("trackName");
                    let th = document.getElementById("trackid");
                    let trackDuration = document.getElementById("trackDuration");
                    let trackGenre = document.getElementById("trackGenre");
                    let trackAlbum = document.getElementById("trackAlbum");
                    let trackArtist = document.getElementById("trackArtist");
                    let trackLyrics = document.getElementById("trackLyrics");

                 title.append(trackData.name);
                 th.append(trackData.id.toString());

                 


/*                  if(seconds==0){
                     seconds= seconds.toString()+ "0";
                 } */

                 trackDuration.append(duration(trackData.duration));
                 trackGenre.append(trackData.album.genre.name);
                 trackAlbum.append(trackData.album.name);

                 document
                 .querySelector("#trackAlbum")
                 .addEventListener("click", function (stop) {
                   stop.preventDefault();
                  
                   // Send to createItem with params
                   window.location.replace("album.html?id="+trackData.album.id);
               
                 });


                 getArtist(trackData.album.id);
               //  trackArtist.append(trackData.album.artist.name);
                 trackLyrics.append(trackData.lyrics);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

function getArtist(id){
    fetch('http://localhost:8082/albums/read/' + id)
    .then(
        function (response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }

            // Examine the text in the response
            response.json().then(function (albumData) {
                trackArtist.append(albumData.artist.name);
                document
                .querySelector("#trackArtist")
                .addEventListener("click", function (stop) {
                  stop.preventDefault();
                 
                  // Send to createItem with params
                  window.location.replace("artist.html?id="+albumData.artist.id);
              
                });
            });
        }
    )
    .catch(function (err) {
        console.log('Fetch Error :-S', err);
    });
}


const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getAlbum(id);