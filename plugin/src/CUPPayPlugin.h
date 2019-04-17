//
//  UPPayPlugin.h
//
//  Created by anasit on 2019/01/02.
//  Copyright © 2018年 anasit. All rights reserved.
//

#import <Cordova/CDVPlugin.h>

@interface CUPPayPlugin : CDVPlugin
{
}
- (void)pay:(CDVInvokedUrlCommand*)command;
- (void)testPay:(CDVInvokedUrlCommand*)command;
@end
