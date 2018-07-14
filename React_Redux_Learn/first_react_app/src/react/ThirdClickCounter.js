import React from 'react';
import { stringFormat } from '../Utilities';

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

    var content = stringFormat('The third counter: {0}', this.props.currentValue);
    return (
      <div>
        <div>{content}</div>
      </div>
    );
  }
}

export default ThirdClickCounter;