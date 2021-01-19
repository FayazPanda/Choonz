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
  
                    let title = document.getElementById("trackName");
                    let th = document.getElementById("trackid");
                    let trackDuration = document.getElementById("trackDuration");
                    let trackLyrics = document.getElementById("trackLyrics");

                 title.append(trackData.name);
                 th.append(trackData.id.toString());
                 trackDuration.append(trackData.duration.toString());
                 trackLyrics.append(trackData.lyrics);
                });
            }
        )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });
}

/* function trackRow(id, name, duration){
    return '<tr>\
    <th scope="row">'+id+'</th>\
    <td>'+name+'</td>\
    <td>'+duration+'</td>\
  </tr>'
} */

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');
console.log(id);

getAlbum(id);