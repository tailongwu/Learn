import React from 'react';

class FirstClickCounter extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <div>The first counter</div>
        <div>{this.props.currentValue}</div>
      </div>
    );
  }
}

export default FirstClickCounter;