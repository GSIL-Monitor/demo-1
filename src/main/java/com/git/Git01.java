package com.git;

/**
 * @Author suosong
 * @Date 2018/8/28

    几个重要概念：
    工作区：编辑的环境
    暂存区：add把文件从工作区放入暂存区
    分支：  commit把文件从暂存区推到分支


    我们把文件往Git版本库里添加的时候，是分两步执行的：

        第一步是用git add把文件添加进去，实际上就是把文件修改添加到暂存区；

        第二步是用git commit提交更改，实际上就是把暂存区的所有内容提交到当前分支。

        因为我们创建Git版本库时，Git自动为我们创建了唯一一个master分支，所以，现在，git commit就是往master分支上提交更改。

        你可以简单理解为，需要提交的文件修改通通放到暂存区，然后，一次性提交暂存区的所有修改。


     版本回退：
        版本回退指的是回退到本地commit的版本。如果多人开发一个项目，可以看到每次其他人commit的代码。
        git log   //查看commit 的id
        git reset --head "commit id"   //commit id 没有必要写全，有个五六位就好了
        如果版本回退了，想恢复版本，此时git log是找不到最新的版本的。只能到现在的版本记录。
        git reflog     //这个命令可以帮我们找到最新的（本地历史）



    撤销修改:
        1,如果此时已经commit，那么不属于撤销修改的范畴，看上面的版本回退就可以了
        2，如果此时没有add到暂存区，那么使用命令 git checkout -- file
        3,如果此时已经add，需要先把文本从暂存区回退到工作区 git reset HEAD file
            再重复第2步就可以了
        4,如果已经add，而且不想要工作区的文本，可以直接执行 git checkout HEAD file 直接回退到了上一次commit时的文本


    本地代码推到远程库
        git push  origin master  //origin 是远程库的默认名，也可以修改   master是远程分支


    创建分支，切换分支，查看分支,合并分支,删除分支
        git branch dev
        git checkout dev
        git branch
        git merge dev
        git branch -d dev


    查看日志分支图
        git log --graph --pretty=oneline --abbrev-commit
        git log --graph


    应用场景：新功能在dev上开发了一半，这个时候线上出现了一个bug，需要紧急修复，好的习惯是创建一个新的bug分支，解决后把bug分支合并，然后删除分支
            但是dev上由于代码没有提交，导致无法切换分支。此时就需要一个保留现场的功能。
        git stash     此条命令一执行，恢复到上一个commit状态，新写的代码会消失
            等修复完bug，切回dev分支的时候，这个时候需要恢复现场
        git stash list   可以看到保存了几次现场
        git stash pop    可以恢复现场 如果不想恢复所有现场，那么可以用 git stash apply "现场号"     来恢复，现场号在git stash list中可以找到


    在旧版的git中，如果没有commit，是不允许切换分支的。但是在新版中，是允许带着工作区切换分支的。
    比如在A分支编辑的内容，没有commit，切换分支，会带到B分支，如果B分支commit了这部分内容，那么这部分内容就属于B分支了。

    建议的操作就是用 git stash保留现场，然后切换分支



 */
public class Git01 {
}
