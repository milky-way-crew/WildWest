<!-- invite -->
	<div class="container">
		<div id="invite" class="modal" style="display: none;">
			<img id="door" src="resources/img/door.gif">
			<div id="invHeader" class="modal-header">
				<h1>Invitation</h1>
			</div>
			<div id="invBody" class="modal-body">
			<div id="invText" class="well">
				<p></p>
			</div>
		</div>
			<div class="modal-footer">
				<a id="accept" href="#" class="btn btn-success" data-dismiss="modal">Accept
					<i class="icon-white icon-share-alt"></i>
				</a> <a id="reject" class="btn btn-medium btn-warning"
					data-dismiss="modal">Reject</a>
			</div>
		</div>
	</div>
	<!-- invite -->

	<!-- modal window create room -->
	<div class="container">
		<div id="createRoomModal" class="modal" style="display: none;">
			<div class="modal-header">
				<h1>Create room</h1>
			</div>
			<div class="modal-body">
				<p></p>
				<input type="text" id="roomName" size=80
					placeholder="Enter room name...">
				<div class="btn-group">
					<a id="info" class="btn dropdown-toggle btn-info"
						data-toggle="dropdown" href="#">Choose Game <span
						class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a id="Monopoly" tabindex="-1" href="#">Monopoly</a></li>
						<li><a id="Chess" tabindex="-1" href="#">Chess</a></li>
						<li><a id="DrawAndGuess" tabindex="-1" href="#">Draw and
								Guess</a></li>
						<li><a id="Gibbet" tabindex="-1" href="#">Gibbet</a></li>
					</ul>
				</div>
			</div>
			<div class="modal-footer">
				<a id="createRoom" href="#" class="btn btn-success"
					data="modal">Create <i class="icon-white icon-edit"></i>
				</a> <a id="closeModal" class="btn btn-medium btn-warning"
					data-dismiss="modal">Close</a>
			</div>
		</div>
	</div>