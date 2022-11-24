<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sieteislas" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	html,body,.container {
    height:100%;
	}
	.container-fluid {
		display:table;
		width: 100%;
		margin-top: -50px;
		padding: 50px 0 0 0; /*set left/right padding according to needs*/
		box-sizing: border-box;
		background-image: url('/resources/images/board/welcome3.png');
		background-repeat: no-repeat;
		background-size: 100% 100%;
	}

	.row {
		height: 100%;
		display: table-row;
	}

	.row .no-float {
		display: table-cell;
		float: none;
	}
	.board {
		background-image: url('/resources/images/board/board8.png');
  		background-repeat: no-repeat;
		background-size: 100% 100%;
		height: 80%;
	}
	.board-element{
		background-color: rgba(241, 202, 139, 0.811); 
		height: 20%;
	
	}
	.board-border{
		border-style: solid;
		border-width: 5px;
		border-color: wheat;
		border-radius: 10px;
		margin: 2px
	}
	.message-input{
		width: 75%; 
		border-style: solid; 
		border-width: 1px; 
		border-color: wheat; 
		border-radius: 10px; 
		margin-left: 3px;
	}

</style>

<!DOCTYPE html>
<html>
	<sieteislas:htmlHeader/>
	<body class="container-fluid">
		<div class="row">
			<div class="col-md-9 no-float">
				<div class="board board-border">.</div>
				<div class="board-element board-border" >
					<div class="board-border" style="display: inline-block; width: 79%; height: 100%;">CARTAS</div>
					<div class="board-border" style="display: inline-block; float: right; width: 20%; height: 100%;">DADO</div>
				</div>
				
			</div>
			<div class="col-md-3 no-float">
				<div class="board-element board-border">
					<div>Aqui van los mensajes</div>
					<div>
						<form style="bottom: 0;">
							<input type="text" class="message-input">
							<button type="submit" class="btn btn-default">Submit</button>
						</form>
					</div>
					
				</div>
				<div class="board-element board-border">Player1</div>
				<div class="board-element board-border">Player2</div>
				<div class="board-element board-border">Player3</div>
				<div class="board-element board-border">Player4</div>
			</div>
		</div>
		<sieteislas:footer/>
	</body>
</html>