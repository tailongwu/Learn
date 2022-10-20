import * as React from "react"

interface IUser {
    id: number
    name: string
}

interface IState {
    userList: IUser[]
}

export default class UserList extends React.Component<any, IState> {
    constructor(props: any, context: any) {
        super(props, context);

        let userList: IUser[] = [];
        for (let i = 1; i <= 20; i++) {
            userList.push({
                id: i,
                name: "user" + i,
            })
        }

        this.state = {
            userList: userList
        }
    }


    render() {
        return (
            <div>
                <list>

                </list>
            </div>
        )
    }
}