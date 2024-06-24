# QuickCommand
[**中文**](README.md) **|** **[English]**

This is a mod for quickly and consecutively executing multiple arbitrary commands in the game. All commands can be executed by clicking the corresponding buttons in the chat box.

![en_us_example_img](src/main/resources/assets/quickcommand/img/en_us_example_img.png)

---



# Short Key

Integrated a shortcut key to quickly open the interface in the chat box, defaulting to `U`, which can be changed in the game settings.



# Commands

- **/quickCommand**
  - Open the quick command list.
- **/quickCommand add <name> <command>**
  - Add a quick command named <name> with command <command>.
  - Example: /quickCommand add "Execute /seed" "/seed"
    - Add a quick command to execute /seed.
- **/quickCommand remove <name>**
  - Remove the quick command named <name>.
  - Example: /quickCommand remove "Execute /seed"
    - Remove the quick command named \"execute /seed command\".
- **/quickCommand removeAll**
  - Remove all quick commands.
- **/quickCommand removeAll confirm**
  - Confirm to remove all quick commands.
- **/quickCommand listWithRun**
  - Open the quick command list.
- **/quickCommand swap <index1> <index2>**
  - Swap the display positions of <index1> and <index2> in the list.
- **/quickCommand displayCommandInList <true/false>**
  - Whether to display commands in the list.
- **/quickCommand help**
  - View the mod usage instructions.
