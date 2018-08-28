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
        3,如果此时已经add，需要先把文本从暂存区回退到工作区 git checkout HEAD file
            再重复第2步就可以了





 */
public class Git01 {
}
