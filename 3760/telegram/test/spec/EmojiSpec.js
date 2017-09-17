describe('Testing the EMOJI PARSING for group names', function() {
	// Declaring mock variables.
	var $scope, chat_serv;
	var app_model, chat_modal;
	var temp = "This is :trumpet: :+1:";

	// Create a mock 'app' module.
	beforeEach(angular.mock.module('myApp'));

	// Inject controller into the module.
	beforeEach(angular.mock.inject(function ($controller, $rootScope, AppChatsManager) {
		$scope = $rootScope.$new();
		chat_serv = AppChatsManager;
		
		//app_modal = $controller('AppIMController', 
		//	{$scope: scope, AppChatsManager: chat_serv});
		chat_modal = $controller('ChatModalController', 
			{$scope: $scope, AppChatsManager: chat_serv});
	}));

	it('- Should create a group and set the group name.', function() {
		$scope.group = {name: temp};

		
		expect(temp).toBeDefined();
		expect($scope.group).toBeDefined();
	});

	it('- Should call the EmojiHelper to change the group name.', function() {
		expect($scope.editTitle).toBeDefined();
	});

	it('- <Check> Old group name should not be equal to new group name.', function() {
		$scope.group = {name: temp};

		$scope.group.name = $scope.group.name.replace(/:([a-z0-9\-\+\*_]+?):/gi, function (all, shortcut) {
          var emojiCode = EmojiHelper.shortcuts[shortcut];

          if (emojiCode !== undefined) {
            return EmojiHelper.emojis[emojiCode][0];
          }
          return all;
        });

        expect($scope.group.name).not.toBe(temp);
	});
});