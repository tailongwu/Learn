import React from 'react';

import FirstClickCounter from './FirstClickCounter';
import SecondClickCounter from './SecondClickCounter';
import ThirdClickCounter from './ThirdClickCounter';
import Time from './Time';

class Test extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      count: 0
    };
  }

  render() {
    return (
      <div>
        <button onClick={() => {
          this.setState({
            count: this.state.count + 1
          });
        }}>
          +
        </button>
        <button onClick={() => {
          console.log('Before ' + this.state.count);
          this.setState({
            count: this.state.count - 1
          });
          console.log('After ' + this.state.count);
        }}>
          -
        </button>
        <div>{this.state.count}</div>
        <FirstClickCounter currentValue={this.state.count} />
        <SecondClickCounter currentValue={this.state.count} />
        <ThirdClickCounter currentValue={this.state.count } />
        <Time />
      </div>
    );
  }
}

export default Test;
