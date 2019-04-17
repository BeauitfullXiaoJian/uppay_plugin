#import "CUPPayPlugin.h"
#import "UPPaymentControl.h"
@interface CUPPayPlugin()

@property(nonatomic,strong) NSString *appScheme;
@property(nonatomic,strong) NSString *callbackId;

@end

@implementation CUPPayPlugin

- (void)pay:(CDVInvokedUrlCommand *)command{
    NSString* tn = [command.arguments objectAtIndex:0];
    _appScheme = @"uppayplugin";
    _callbackId = command.callbackId;
    [[UPPaymentControl defaultControl] startPay:tn fromScheme:_appScheme mode:@"00" viewController:self.viewController ];
}

- (void)payTest:(CDVInvokedUrlCommand *)command{
    NSLog(@"支付测试");
    NSString* tn = [command.arguments objectAtIndex:0];
    _appScheme = @"uppayplugin";
    _callbackId = command.callbackId;
    [[UPPaymentControl defaultControl] startPay:tn fromScheme:_appScheme mode:@"01" viewController:self.viewController ];
}

-(void) handleOpenURL:(NSNotification *)notification{
    NSLog(@"通知消息");
    NSURL* url = [notification object];
    [[UPPaymentControl defaultControl] handlePaymentResult:url completeBlock:^(NSString *code, NSDictionary *data) {
        NSLog(@"reslut = %@",code);
        NSDictionary *resultData = @{@"payResult": code, @"resultData": data==nil?@"":data};
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultData];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:_callbackId];
    }];
}

@end
