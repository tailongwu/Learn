import React from 'react';

class Time extends React.Component {
  constructor() {
    super();
    this.state = {
      time: undefined
    };
  }

  componentWillMount() {
    setInterval(() => {
      this.setState({
        time: new Date().toLocaleTimeString()
      });
    }, 1000);
  }

  render() {
    return this.ShowTime();
  }

  ShowTime() {
    return (
      <div>
        {this.state.time}
      </div>
    );
  }
}

export default Time;
