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
        scope.newGameData = null;

        scope.newGameOptions = {
            availablePieces:[
                {name: 'X'},
                {name: 'O'}
            ],
            selectedPiece : {name : 'O'},

        };

        scope.createNewGame = function (){
            var data = scope.newGameData;
            var params = JSON.stringify(data);
            validateCreateGame();
            http.post("/game/create",params,{
                headers:{
                    'Content-Type' : 'application/json; charset=UTF-8'
                }
            }).success(function (data,status,headers,config){
                rootScope.gameId = data.id;
                location.path('/game/'+rootScope.gameId);

            }).error(function(data,status,headers,config){
                location.path('/player-panel');
            })
        }
    }
]);

ticTacToe.controller('playerGamesController', ['$scope', '$http', '$location', '$routeParams',

    function(scope,http,location,routeParams){

        scope.playerGames = [];

        http.get('/game/player/list').success(function(data){
            scope.playerGames  = data;
        }).error(function(data,status,headers,config){
            location.path('/player-panel');
        })

        scope.loadGame = function (id){
            console.log(id);
            location.path('/game/' + id);
        }
    }
])


ticTacToe.controller('gameController', function($rootScope, $routeParams, $scope, $http){
    var gameStatus;
    getInitialData();

    function getInitialData(){
        $http.get('/game/' + $routeParams.id).success(function(data){
            $scope.gameProperties = data;
            gameStatus = $scope.gameProperties.gameStatus;
            getMoveHistory();
        }).error(function (data,status,headers,config){
            $scope.errorMessage = "Failed to load game properties";
        });
    }

    function getMoveHistory(){
        $scope.movesInGame = [];

        return $http.get('/move/list').success(function(data){
            $scope.movesInGame  = data;
            $scope.playerMoves = [];

            //fill the board with positions from the retrieved moves
            angular.forEach($scope.movesInGame,function(move){
                if(move.boardZ == 0){
                    $scope.rowsLayerOne[move.boardX][move.boardY].letter = move.piece;
                }
                else if(move.boardZ == 1){
                    $scope.rowsLayerTwo[move.boardX][move.boardY].letter = move.piece;
                }
                else if(move.boardZ == 2){
                    $scope.rowsLayerThree[move.boardX][move.boardY].letter = move.piece;
                }
            })
        }).error(function (data,status,headers,config){
            $scope.errorMessage = "Failed to load moves in game";
        })
    }

    function checkPlayerTurn(){
        return $http.get('/move/turn').success(function(data){
            $scope.playerTurn = data;
        }).error(function (data,status,headers,config){
            $scope.errorMessage = "Failed to get player turn";
        })
    }

    function getNextMove(){
        $scope.nextMoveData = [];

        //Computer is a second player
        if(!$scope.gameProperties.secondPlayer){
            $http.get("/move/autocreate").success(function (data, status, headers, config){
                $scope.nextMoveData = data;
                getMoveHistory().success(function(){
                    var gameStatus = $scope.movesInGame[$scope.movesInGame.length - 1].gameStatus;
                    if(gameStatus != 'IN_PROGRESS'){
                        alert(gameStatus);
                    }
                });
            }).error(function (data,status,headers,config){
                $scope.errorMessage = "Can't send the move";
            })
        }
        else{
            console.log(' another player move');
        }
    }

    function checkIfBoardCellAvailable(boardRow, boardColumn, boardLayer){
        for(var i=0;i<$scope.movesInGame.length;i++){
            var move = $scope.movesInGame[i];
            if(move.boardX == boardRow && move.boardY == boardColumn && move.boardZ == boardLayer){
                return false
            }
        }
        return true;
    }

    $scope.rowsLayerOne = [
        [
            {'id': '000', 'letter': '', 'class': 'box'},
            {'id': '010', 'letter': '', 'class': 'box'},
            {'id': '020', 'letter': '', 'class': 'box'}
        ],
        [
            {'id': '100', 'letter': '', 'class': 'box'},
            {'id': '110', 'letter': '', 'class': 'box'},
            {'id': '120', 'letter': '', 'class': 'box'}
        ],
        [
            {'id': '200', 'letter': '', 'class': 'box'},
            {'id': '210', 'letter': '', 'class': 'box'},
            {'id': '220', 'letter': '', 'class': 'box'}
        ]
    ];

    $scope.rowsLayerTwo = [
        [
            {'id': '001', 'letter': '', 'class': 'box'},
            {'id': '011', 'letter': '', 'class': 'box'},
            {'id': '021', 'letter': '', 'class': 'box'}
        ],
        [
            {'id': '101', 'letter': '', 'class': 'box'},
            {'id': '111', 'letter': '', 'class': 'box'},
            {'id': '121', 'letter': '', 'class': 'box'}
        ],
        [
            {'id': '201', 'letter': '', 'class': 'box'},
            {'id': '211', 'letter': '', 'class': 'box'},
            {'id': '221', 'letter': '', 'class': 'box'}
        ]
    ];

    $scope.rowsLayerThree = [
        [
            {'id': '002', 'letter': '', 'class': 'box'},
            {'id': '012', 'letter': '', 'class': 'box'},
            {'id': '022', 'letter': '', 'class': 'box'}
        ],
        [
            {'id': '102', 'letter': '', 'class': 'box'},
            {'id': '112', 'letter': '', 'class': 'box'},
            {'id': '122', 'letter': '', 'class': 'box'}
        ],
        [
            {'id': '202', 'letter': '', 'class': 'box'},
            {'id': '212', 'letter': '', 'class': 'box'},
            {'id': '222', 'letter': '', 'class': 'box'}
        ]
    ];

    angular.forEach($scope.rowsLayerOne,function(row){
        row[0].letter = row[1].letter = row[2].letter = '';
        row[0].class = row[1].class = row[2].class = 'box';
    })
    angular.forEach($scope.rowsLayerTwo,function(row){
        row[0].letter = row[1].letter = row[2].letter = '';
        row[0].class = row[1].class = row[2].class = 'box';
    })
    angular.forEach($scope.rowsLayerThree,function(row){
        row[0].letter = row[1].letter = row[2].letter = '';
        row[0].class = row[1].class = row[2].class = 'box';
    })
    $scope.markPlayerMove = function (column){
        checkPlayerTurn().success(function(){
            console.log(column);
            var boardX = parseInt(column.id.charAt(0));
            var boardY = parseInt(column.id.charAt(1));
            var boardZ = parseInt(column.id.charAt(2));
            var params = {'boardX':boardX,'boardY':boardY,'boardZ':boardZ}

            if(checkIfBoardCellAvailable(boardX,boardY,boardZ)){
                //if Player has turn
                if($scope.playerTurn == true){
                    $http.post("/move/create", params,{
                        headers:{
                            'Content-Type':'application/json; charset=UTF-8'
                        }
                    }).success(function(){
                        getMoveHistory().success(function(){
                            var gameStatus = $scope.movesInGame[$scope.movesInGame.length - 1].gameStatus;
                            if(gameStatus == 'IN_PROGRESS'){
                                getNextMove();
                            }else{
                                alert(gameStatus);
                            }
                        });
                    }).error(function (data,status,headers,config){
                        $scope.errorMessage = "Can't send the move"
                    });
                }
            }
        });
    };

});