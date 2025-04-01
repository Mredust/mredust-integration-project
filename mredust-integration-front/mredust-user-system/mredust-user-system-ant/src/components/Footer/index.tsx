import {GithubOutlined} from '@ant-design/icons';
import {DefaultFooter} from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      links={[
        {
          key: 'author',
          title: 'Author',
          href: 'https://www.helloimg.com/i/2025/04/01/67eb7f02615d3.jpg',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined/>,
          href: 'https://github.com/Mredust',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
