import React from 'react';

class ThirdClickCounter extends React.Component {

  componentWillReceiveProps(nextProps) {
    console.log('componentWillReceiveProps Props value: ' + this.props.currentValue + ' : ' + nextProps.currentValue);
  }

  shouldComponentUpdate(nextProps) {
    console.log('shouldComponentUpdate Props value: ' + this.props.currentValue + ' : ' + nextProps.currentValue);
    return true;
  }

  componentWillUpdate() {
    console.log('componentWillUpdate');
  }

  render() {
    console.log('render');
    if (this.props.currentValue === 1) {
      this.forceUpdate();
    }
    return (
      <div>
        <div>The third counter</div>
        <div>{this.props.currentValue}</div>
      </div>
    );
  }
}

export default ThirdClickCounter;