import React from 'react';

class SecondClickCounter extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <div>The second counter</div>
        <div>{this.props.currentValue}</div>
      </div>
    );
  }
}

export default SecondClickCounter;