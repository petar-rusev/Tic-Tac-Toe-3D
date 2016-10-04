/**
 * Created by petar on 9/29/2016.
 */
var jq = $.noConflict();

function validateCreateGame(){
    var pieceType = jq('#piece :selected');

    if(pieceType.text()){
        jq('.piece-validation-message').addClass('hidden');
    }
    else{
        jq('.piece-validation-message').removeClass('hidden');
    }
}
ticTacToe.controller('newGameController',['$rootScope','$scope','$http','$location',

    function (rootScope,scope,http,location){

        rootScope.gameId = null;

        scope.createNewGame = function (){
            var data = {"piece":"X"};
            var params = JSON.stringify(data);
            validateCreateGame();
            http.post("/game/create",params,{
                headers:{
                    'Content-Type' : 'application/json; charset=UTF-8'
                }
            }).success(function (data,status,headers,config){
                rootScope.gameId = data.gameId;
                location.path('/game/'+rootScope.gameId);

            }).error(function(data,status,headers,config){
                location.path('/player-panel');
            })
        }
    }
]);

ticTacToe.controller('playerGamesController', ['$scope', '$http', '$location', '$routeParams','$rootScope',

    function(scope,http,location,routeParams,rootScope){

        scope.playerGames = [];

        http.get('/game/player/list').success(function(data){
            console.log(data);
            scope.playerGames  = data;

        }).error(function(data,status,headers,config){
            location.path('/player-panel');
        })

        scope.loadGame = function (id){
            console.log(id);
            rootScope.gameId = Object.keys(id)[0];
            location.path('/game/'+rootScope.gameId);
        }

    }

])


ticTacToe.controller('gameController', function($rootScope, $routeParams, $scope, $http){
    var gameStatus;
    getInitialData();

    function getInitialData(){
        $http.get('/game/' + $routeParams.id).success(function(data){
            console.log(data)
            data = data.gameBoard;
            drawCube(data);
        }).error(function (data,status,headers,config){
            $scope.errorMessage = "Failed to load game properties";
        });
    }



    $scope.markPlayerMove = function (column){

        console.log(column);
        var boardX = parseInt(column.charAt(0));
        var boardY = parseInt(column.charAt(1));
        var boardZ = parseInt(column.charAt(2));
        var params = {'boardX':boardX,'boardY':boardY,'boardZ':boardZ,'gameId':$rootScope.gameId}
        $http.post("/move/create", params,{
            headers:{
                'Content-Type':'application/json; charset=UTF-8'
            }
        }).success(function(data){
            if(data.message != null){
                alert(data.message);

            }
            drawCube(data.gameBoard);
        })

        .error(function (data,status,headers,config){
            $scope.errorMessage = "Can't send the move"
        });
    };
    function drawCube(cubeArray){
        var humanMoves  = 0;
        var computerMoves = 0;
        for(var i=0;i<cubeArray.length;i++){
            for(var j=0;j<cubeArray.length;j++) {
                for(var k=0;k<cubeArray.length;k++) {
                    if(cubeArray[i][j][k] === 1){
                        jq('#'+i+j+k).css('background-color','rgb(255,0,0)');
                        computerMoves++;
                    }else if(cubeArray[i][j][k] === 2){
                        jq('#'+i+j+k).css('background-color','rgb(0,0,255)');
                        humanMoves++;
                    }
                }
            }
        }
        if(humanMoves + computerMoves == 27){
            if(humanMoves > computerMoves){
                alert("Congratulations you won the game. Go to player panel to start new one.");
            }
            else if(humanMoves == computerMoves){
                alert("Draw game");
            }
            else{
                alert("Computer is the winner!");
            }
        }
    }
});