package ufl.cs1.controllers;

import game.controllers.DefenderController;
import game.models.*;

import java.util.*;

public final class StudentController implements DefenderController
{
	public void init(Game game) { }

	public void shutdown(Game game) { }

	public int[] update(Game game,long timeDue)
	{
		int[] actions = new int[Game.NUM_DEFENDER];
		List<Defender> enemies = game.getDefenders();


		return inky(game);
	}

	public int[] inky(Game game)
	{
		int[] actions = new int[Game.NUM_DEFENDER];

		Attacker pacMan = game.getAttacker();
		Defender inky = game.getDefender(3);

		Defender a = game.getDefender(0);
		Defender b = game.getDefender(1);
		Defender c = game.getDefender(2);

		Maze maze = game.getCurMaze();
		List<Node> junction = maze.getJunctionNodes();
		List<Node> powerPill = game.getPowerPillList();
		Node pacLoc = pacMan.getLocation();
		int pacDir = pacMan.getDirection();
		Node inkyLoc = inky.getLocation();
		int inkyDir = inky.getDirection();
		boolean isNearPill= false;
		int pill = 0;
		int temp = 0;
		int min = 0;

		actions[2] = c.getNextDir(pacLoc, true);

		if (powerPill.size() > 0)
		{
			min = pacLoc.getPathDistance(powerPill.get(0));
			for (int i = 0; i < powerPill.size(); i++)
		{
			if (pacLoc.getPathDistance(powerPill.get(i)) <= 2)
			{
				isNearPill = true;
				pill = min;
				break;
			}
			else
			{
				temp = pacLoc.getPathDistance(powerPill.get(i));
				if (temp < min)
				{
					min = temp;
				}
			}
		}

			if (isNearPill)
			{
				if ((pacLoc.getPathDistance(a.getLocation()) > 50) && (pacLoc.getPathDistance(b.getLocation()) > 50) && (pacLoc.getPathDistance(c.getLocation()) > 50))
				{
					actions[3] = inky.getNextDir(pacLoc, true);
				}
				else
				{
					actions[3] = inky.getNextDir(pacLoc, false);
				}
			}
			else if(inkyLoc.getPathDistance(pacLoc) > 10)
			{
				actions[3] = inky.getNextDir(pacLoc, true);
			}
			else if(min > 10)
			{
				actions[3] = inky.getNextDir(pacLoc, true);
			}
			else
			{
				actions[3] = inky.getNextDir(pacLoc, false);
			}
		}
		else
		{
			actions[3] = inky.getNextDir(pacLoc, true);
		}

		//actions[3] = inky.getNextDir(pacLoc, true);
		//if (pacMan.getDirection() == inky.getDirection())
		//	actions[3] = inky.getReverse();
		//if (a.getLocation().isPill())
		//	actions[0] = a.getReverse();

		return actions;
	}

}